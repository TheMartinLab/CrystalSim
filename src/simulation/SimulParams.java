/*******************************************************************************
 * Copyright (c) 2013 Eric Dill -- eddill@ncsu.edu. North Carolina State University. All rights reserved.
 * This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Eric Dill -- eddill@ncsu.edu - initial API and implementation
 * 	James D. Martin -- jdmartin@ncsu.edu - Principal Investigator
 ******************************************************************************/
package simulation;
import java.io.File;

import shapes.ShapeTypes;

import geometry.JVector;

public class SimulParams {
	public Nucleation initNuc = Nucleation.Initial;
	public int nucInitVal = 1;
	
	public Nucleation contNuc = Nucleation.Continuous;
	public double nucContVal = .05;
	
	public NucleationOrientation nucOrient = NucleationOrientation.Random;
	public JVector[][] nucOrientVal = JVector.axes100;
	public double[] axialGrowthRates = { .5 , 0, 0};
	
	public NucleationLocation nucLoc = NucleationLocation.Random;
	public JVector[] nucLocOptions = JVector.cube_corners;
	
	public ShapeTypes nucShape = ShapeTypes.Spherical;
	
	public Termination term = Termination.FractionComplete;
	public Object termVal = new Double(1);
	
	public ShapeTypes sampleShape = ShapeTypes.Cubic;
	public JVector[] sampleUnitAxes = JVector.v100sU;
	public int[] sampleUnitsPerAxis = {200, 200, 200};
	
	public File outXYZ = new File("Simulation.xyz");
	public File outTransformed = new File("Simulation.txt");
	
	public double timeStep = 0.5;
	
	public int maxNumCrystals = Integer.MAX_VALUE;	
}
