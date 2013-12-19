package simulation;

import geometry.JVector;
import io.MyFileInputStream;
import io.MyPrintStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

import chemistry.JAtom;

public class ExplodeCrystal {

	private JAtom[] atoms;
	private int numAtomTypes;
	private double boomFactor;
	private String fileRoot;
	private JVector sampleCM;
	private int numAtoms;
	
	/**
	 * 
	 * @param file String that points to a .xyz file WITHOUT THE .XYZ EXTENSION
	 * @param boom_factor > 0 indicates that the output will be an exploded view.  < 0 indicates that the output will be an imploded view
	 */
	public ExplodeCrystal(String fileRoot, double boom_factor) {
		this.fileRoot = fileRoot;
		this.boomFactor = boom_factor;
	}
	public void run() {
		readFile();
		explode();
		print();
	}
	private void readFile() {
		Scanner s = (new MyFileInputStream(fileRoot + ".xyz")).getScanner();
		String[] line;
		numAtoms = s.nextInt();
		atoms = new JAtom[numAtoms];
		s.nextLine();
		s.nextLine();
		double x, y, z;
		int Z, maxZ = 0;
		int idx = 0;
		sampleCM = new JVector();
		while (s.hasNextLine()) {
			line = s.nextLine().split("\t");
			Z = Integer.valueOf(line[0]);
			if(Z > maxZ) { maxZ = Z; }
			x = Double.valueOf(line[1]);
			y = Double.valueOf(line[2]);
			z = Double.valueOf(line[3]);
			sampleCM.i += x;
			sampleCM.j += y;
			sampleCM.k += z;
			atoms[idx++] = new JAtom(Z, x, y, z);
		}
		numAtomTypes = maxZ;
		sampleCM = JVector.multiply(sampleCM, 1./((double) numAtoms));
	}
	private JVector[] getDisplacementVectors() {
		JVector[] displace = new JVector[numAtomTypes];
		int[] numAtomsPer = new int[numAtomTypes];
		JVector CM = new JVector();
		for(int i = 0; i < displace.length; i++) {
			displace[i] = new JVector();
		}
		int Z;
		JVector pos;
		for(int i = 0; i < atoms.length; i++) {
			pos = atoms[i].getPosition();
			Z = atoms[i].getZ();
			displace[Z-1] = JVector.add(displace[Z-1], pos);
			numAtomsPer[Z-1]++;
		}
		for(int i = 0; i < displace.length; i++) {
			displace[i] = JVector.multiply(displace[i], 1./((double) numAtomsPer[i]));
			displace[i] = JVector.subtract(displace[i], sampleCM);
			displace[i] = JVector.multiply(displace[i], boomFactor);
		}
		return displace;
	}
	private void explode() {
		JVector[] displacement = getDisplacementVectors();
		int Z;
		for(int i = 0; i < atoms.length; i++) {
			Z = atoms[i].getZ();
			atoms[i].translate(displacement[Z-1]);
		}
		
	}
	
	private void print() {
		File out = new File(fileRoot + "_" + "explodedBy_" + boomFactor + ".xyz");
		MyPrintStream mps = new MyPrintStream(out);

		mps.println(numAtoms + "\n");
		for(int i = 0; i < atoms.length; i++) {
			mps.println(atoms[i].toStringForXYZ());
		}
		mps.close();
	}
	public static void main(String[] args) {
		String root = "2-15-2013_14-45-1";
		double boomFactor = 1.5;
		ExplodeCrystal ec = new ExplodeCrystal(root, boomFactor);
		ec.run();
	}
}