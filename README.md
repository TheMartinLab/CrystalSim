CrystalSim
==============

Where has this been used?
-------------------------
[![DOI](https://zenodo.org/badge/6651/TheMartinLab/CrystalSim.png)](http://dx.doi.org/10.5281/zenodo.12264)

**Manuscript title** "Crystal Growth Simulations To Establish Physically 
Relevant Kinetic Parameters from the Empirical Kolmogorov–Johnson–Mehl–Avrami Model". 

**Reference** Chem. Mater., 2013, 25 (20), pp 3941–3951

**DOI** [10.1021/cm402751x] (http://pubs.acs.org/doi/abs/10.1021%2Fcm402751x)

What problem does this solve?
-----------------------------
CrystalSim is a GUI environment for simulating various aspects of the crystallization process 
from a geometrical context.  It allows the user to select:

- the crystal habit (cubic, orthorhombic, cylindrical, etc...)
- the sample container shape (same options as crystal shape)
- the growth rate of individual crystal axes
- the size of the sample container
- the number of crystallites to nucleate
- the rate of crystallite nucleation
- which of the three parameters in the Avrami expression to fit (k, t0, n)
- the number of simulations to repeat with the same set of initial parameters

CrystalSim will then output a number of files for subsequent analysis including

- output/
  - fits/
    - contains **(N+1)** fitting files for each simulation where **N** is the number of simulated 
      crystallites and **+1** is the fit to the 'bulk' transformation.
    - The first line contains the fitting parameters in order (k, t0, n) separated by tabs
    - The remaining lines contain three values (t, normalized transformation, fit to 
      normalized transformation) that are tab delimited. File ends with two blank lines
  - structures/
    - contains **M** .xyz files where **M** is the number of simulations that were run.  
    - The first line contains the number of voxels in the simulation volume
    - There is then at least one section that contains a line with a single number and then a 
      long list of lines that contain four numbers.  The single number is the frame number and
      instructs [VMD] (http://www.ks.uiuc.edu/Research/vmd/) to treat the xyz file as a movie 
      if there is more than one section.  The list of lines is formatted as (crystal_idx, x, y, z) 
      and is tab-delimited.
  - transformations/
    - Contains **M** .trans files where **M** is the number of simulations that were run
    - The first block (separated by lines of `=`) contains the starting parameters
    - The second block contains a reduced snapshot of the simulation at each time step with a number of columns
      - Column 1: time
      - Column 2: total simulation voxels (used for debugging)
      - Column 3: total number of newly transformed voxels
      - Column 4: total transformed voxels
      - Remaining columns: number of transformed voxels for individual crystallites
    - The third block contains information about each of the crystallites that grew including 
      - crystal shape
      - orientation axes
      - axial growth rates
      - initial dimensions
      - nucleation time
      - nucleation location
      - fitting parameters
      - error in fitting parameters
    - The fourth and final block contains **N+2** rows that are tab delimited can be copied directly into excel
      - The first row contains column headings
      - The remaining rows outline the fitting parameters for **N** crystals and **+1** for the bulk fit
  - automated fitting.txt
    - a tab-delimited ascii file that is the aggregate of the fourth block of information in each of the `transformations/` files
  - fits.zip
    - A zipped file of the `fits/` folder.  This is always output, while the `fits/` folder is an optional output
  - java object.obj
    - A java object that can be reloaded at a later date such that the simulation can be re-performed and/or the analysis can be re-run

Disclaimer
----------
This software is not yet guaranteed to run flawlessly and critical errors may be experienced.  
Please let me know if you run into problems (edill@bnl.gov).

Development in progress, use at own risk! Contact: (Developer) Dr. Eric Dill edill@bnl.gov; 
(Principal Investigator) Professor James Martin martinjd@ncsu.edu

Installation
------------
The Java Runtime Environment (JRE) version 1.7 is required to run this software

Runnable .jars (binary) are available on the release page

Development
-----------
See section 2 of `doc/Manual.pdf`

A class diagram is available in the 'doc' folder.

This software was developed in the Eclipse IDE (Kepler) with Java 1.7.  This repo can be used 
directly from within Eclipse with the EGit addin.

Further Reading
---------------

See "Readme.docx" in the 'doc' folder.


