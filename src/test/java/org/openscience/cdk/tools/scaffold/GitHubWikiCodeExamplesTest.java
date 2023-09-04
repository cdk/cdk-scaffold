/*
 * Copyright (c) 2022 Julian Zander <zanderjulian@gmx.de>
 *                    Jonas Schaub <jonas.schaub@uni-jena.de>
 *                    Achim Zielesny <achim.zielesny@w-hs.de>
 *                    Christoph Steinbeck <christoph.steinbeck@uni-jena.de>
 *
 * Contact: cdk-devel@lists.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.openscience.cdk.tools.scaffold;

import org.junit.jupiter.api.Test;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;

import java.util.Arrays;
import java.util.List;

/**
 * Test class with executable code examples from the GitHub wiki of CDK Scaffold.
 * The cdk-scaffold module makes versatile molecular scaffold functionalities available for integration with CDK-based
 * workflows and software. All details of the functionality and implementation are described in
 * <a href="https://doi.org/10.1186/s13321-022-00656-x">Schaub et al. "Scaffold Generator: a Java library implementing molecular scaffold functionalities in the Chemistry Development Kit (CDK)" (J Cheminform 14, 79, 2022)</a>.
 *
 * @author Jonas Schaub (jonas.schaub@uni-jena.de)
 * @version 0.0.0.1
 */
public class GitHubWikiCodeExamplesTest {
    /**
     * All scaffold functionalities are accessed via the ScaffoldGenerator class. When it is instantiated, all settings
     * are in default. See the article above for descriptions of the available settings.
     */
    @Test
    public void settingsTest() throws Exception {
        //instantiate Scaffold Generator
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        //print settings; all of them can be configured via the respective set() methods; all settings can be restored to default values using restoreDefaultSettings()
        System.out.println("\nSettings:");
        //scaffold definition to use, see below for details
        System.out.println("- Scaffold mode: " + tmpScaffoldGen.getScaffoldModeSetting());
        //double bonds can be introduced to retain correct hybridisations in scaffold extraction and dissection in aromatic systems only or in all cases (if possible)
        System.out.println("- Retain hybridisations only at aromatic bonds: " + tmpScaffoldGen.areOnlyHybridisationsAtAromaticBondsRetained());
        //aromaticity determinations are performed at multiple steps using the set aromaticity model
        System.out.println("- Determine aromaticity: " + tmpScaffoldGen.isAromaticityDetermined());
        //default is aromaticity model constructed from ElectronDonation.cdk() and Cycles.cdkAromaticSet()
        System.out.println("- Aromaticity model: " + tmpScaffoldGen.getAromaticityModel());
        //the scaffold tree scaffold dissection prioritisation rule nr. 7 can be turned off individually because it takes long to apply
        System.out.println("- Apply rule 7 of scaffold tree prioritization: " + tmpScaffoldGen.isRuleSevenApplied());
        //SmilesGenerator class instance used to determine molecule identity when constructing scaffold trees and networks;
        // default is SmiFlavor.Unique (does *not* encode stereochemistry) in combination with SmiFlavor.UseAromaticSymbols
        System.out.println("- SMILES Generator: " + tmpScaffoldGen.getSmilesGenerator());
        /*
        Output:
        Settings:
        - Scaffold mode: SCAFFOLD
        - Retain hybridisations only at aromatic bonds: false
        - Determine aromaticity: true
        - Aromaticity model: org.openscience.cdk.aromaticity.Aromaticity@30b7c004
        - Apply rule 7 of scaffold tree prioritization: true
        - SMILES Generator: org.openscience.cdk.smiles.SmilesGenerator@79efed2d
        */
    }
    //
    /**
     * Five different structural scaffold definitions can be used with different levels of abstraction. See the article
     * above for details.
     */
    @Test
    public void testDifferentScaffoldModes() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpFlucloxacillin = tmpSmiPar.parseSmiles("CC1=C(C(=NO1)C2=C(C=CC=C2Cl)F)C(=O)NC3C4N(C3=O)C(C(S4)(C)C)C(=O)O");
        //generate scaffold and saturate with implicit hydrogen atoms
        //scaffold (default scaffold mode option): ring systems, linkers, and exocyclic and exolinker double bonds
        IAtomContainer tmpFlucloxacillinScaffold = tmpScaffoldGen.getScaffold(tmpFlucloxacillin, true);
        System.out.println("\nScaffold of Flucloxacillin: " + tmpSmiGen.create(tmpFlucloxacillinScaffold));
        //Murcko framework: ring systems and linkers
        tmpScaffoldGen.setScaffoldModeSetting(ScaffoldGenerator.ScaffoldModeOption.MURCKO_FRAMEWORK);
        tmpFlucloxacillinScaffold = tmpScaffoldGen.getScaffold(tmpFlucloxacillin, true);
        System.out.println("Murcko framework of Flucloxacillin: " + tmpSmiGen.create(tmpFlucloxacillinScaffold));
        //elemental wire frame: all multi bond abstracted to single bonds
        tmpScaffoldGen.setScaffoldModeSetting(ScaffoldGenerator.ScaffoldModeOption.ELEMENTAL_WIRE_FRAME);
        tmpFlucloxacillinScaffold = tmpScaffoldGen.getScaffold(tmpFlucloxacillin, true);
        System.out.println("Elemental wire frame of Flucloxacillin: " + tmpSmiGen.create(tmpFlucloxacillinScaffold));
        //basic framework: all hetero atoms abstracted to carbon atoms
        tmpScaffoldGen.setScaffoldModeSetting(ScaffoldGenerator.ScaffoldModeOption.BASIC_FRAMEWORK);
        tmpFlucloxacillinScaffold = tmpScaffoldGen.getScaffold(tmpFlucloxacillin, true);
        System.out.println("Basic framework of Flucloxacillin: " + tmpSmiGen.create(tmpFlucloxacillinScaffold));
        //basic wire frame: all atoms abstracted to carbon atoms and all bonds abstracted to single bonds
        tmpScaffoldGen.setScaffoldModeSetting(ScaffoldGenerator.ScaffoldModeOption.BASIC_WIRE_FRAME);
        tmpFlucloxacillinScaffold = tmpScaffoldGen.getScaffold(tmpFlucloxacillin, true);
        System.out.println("Basic framework of Flucloxacillin: " + tmpSmiGen.create(tmpFlucloxacillinScaffold));
        /*
        Output:
        Scaffold of Flucloxacillin: O=C(NC1C(=O)N2CCSC21)c3conc3-c4ccccc4
        Murcko framework of Flucloxacillin: n1occ(c1-c2ccccc2)CNC3CN4CCSC43
        Elemental wire frame of Flucloxacillin: O1NC(C(C1)CNC2CN3CCSC32)C4CCCCC4
        Basic framework of Flucloxacillin: c1ccc(cc1)C2=CCC=C2CCC3CC4CCCC34
        Basic framework of Flucloxacillin: C1CCC(CC1)C2CCCC2CCC3CC4CCCC43
        */
    }
    //
    /**
     * The building blocks of scaffolds (rings and linkers) and the removed side chains can be extracted separately.
     */
    @Test
    public void testScaffoldBuildingBlockExtraction() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpFlucloxacillin = tmpSmiPar.parseSmiles("CC1=C(C(=NO1)C2=C(C=CC=C2Cl)F)C(=O)NC3C4N(C3=O)C(C(S4)(C)C)C(=O)O");
        //single rings as they would be represented in the generated scaffold
        List<IAtomContainer> tmpScaffoldRings = tmpScaffoldGen.getRings(tmpFlucloxacillin, true);
        System.out.println("\nRings of Flucloxacillin scaffold:");
        for (IAtomContainer tmpRing : tmpScaffoldRings) {
            System.out.println(tmpSmiGen.create(tmpRing));
        }
        //the one linker chain of Flucloxacillin
        List<IAtomContainer> tmpLinkers = tmpScaffoldGen.getLinkers(tmpFlucloxacillin, true);
        System.out.println("\nLinkers of Flucloxacillin scaffold:");
        for (IAtomContainer tmpLinker : tmpLinkers) {
            System.out.println(tmpSmiGen.create(tmpLinker));
        }
        //side chains of Flucloxacillin, three methyl groups, one chlorine and one fluorine substituent, and a carbonic acid group; keto groups connected to rings and linkers are excluded because they are included in the scaffold
        List<IAtomContainer> tmpSideChains = tmpScaffoldGen.getSideChains(tmpFlucloxacillin, true);
        System.out.println("\nSide chains of Flucloxacillin:");
        for (IAtomContainer tmpSideChain : tmpSideChains) {
            System.out.println(tmpSmiGen.create(tmpSideChain));
        }
        /*
        Output:
        Rings of Flucloxacillin scaffold:
        n1occc1
        c1ccccc1
        O=C1NCC1
        S1CNCC1

        Linkers of Flucloxacillin scaffold:
        O=CN

        Side chains of Flucloxacillin:
        C
        Cl
        F
        C
        C
        O=CO
        */
        //side chains of Flucloxacillin: three methyl groups, one chlorine and one fluorine substituent, and a carbonic acid group;
        // keto groups connected to rings and linkers are excluded because they are included in the scaffold
    }
    //
    /**
     * Scaffolds can be dissected into so-called parent scaffolds according to two methodologies (see article and below).
     */
    @Test
    public void testParentScaffoldGeneration() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpFlucloxacillin = tmpSmiPar.parseSmiles("CC1=C(C(=NO1)C2=C(C=CC=C2Cl)F)C(=O)NC3C4N(C3=O)C(C(S4)(C)C)C(=O)O");
        //1. according to the scaffold network approach, generate all possible parent scaffolds
        List<IAtomContainer> tmpParentScaffolds = tmpScaffoldGen.applyEnumerativeRemoval(tmpFlucloxacillin);
        System.out.println("\nEnumeration of all possible parent scaffolds of Flucloxacillin:");
        for (IAtomContainer tmpParentScaffold : tmpParentScaffolds) {
            System.out.println(tmpSmiGen.create(tmpParentScaffold));
        }
        //2. generate only those parent scaffolds that are prioritized according to the scaffold tree rules
        tmpParentScaffolds = tmpScaffoldGen.applySchuffenhauerRules(tmpFlucloxacillin);
        System.out.println("\nPrioritized parent scaffolds of Flucloxacillin according to the scaffold tree rules:");
        for (IAtomContainer tmpParentScaffold : tmpParentScaffolds) {
            System.out.println(tmpSmiGen.create(tmpParentScaffold));
        }
        /*
        Output:
        Enumeration of all possible parent scaffolds of Flucloxacillin:
        O=C(NC1C(=O)N2CCSC21)c3conc3-c4ccccc4
        O=C(NC1C(=O)N2CCSC21)c3cnoc3
        O=C(NC1C(=O)NC1)c2conc2-c3ccccc3
        O=C1N2CCSC2C1
        O=C(NC1C(=O)NC1)c2cnoc2
        n1occc1-c2ccccc2
        S1CNCC1
        O=C1NCC1
        n1occc1
        c1ccccc1

        Prioritized parent scaffolds of Flucloxacillin according to the scaffold tree rules:
        O=C(NC1C(=O)N2CCSC21)c3conc3-c4ccccc4
        O=C(NC1C(=O)N2CCSC21)c3cnoc3
        O=C1N2CCSC2C1
        O=C1NCC1
        */
    }
    //
    /**
     * Construct a scaffold network from multiple input molecules (see article for details).
     */
    @Test
    public void testScaffoldNetworkGeneration() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpDiazepam = tmpSmiPar.parseSmiles("CN1C(=O)CN=C(C2=C1C=CC(=C2)Cl)C3=CC=CC=C3");
        IAtomContainer tmpBromazepam = tmpSmiPar.parseSmiles("C1C(=O)NC2=C(C=C(C=C2)Br)C(=N1)C3=CC=CC=N3");
        IAtomContainer tmpZolazepam = tmpSmiPar.parseSmiles("CC1=NN(C2=C1C(=NCC(=O)N2C)C3=CC=CC=C3F)C");
        List<IAtomContainer> tmpInputMolecules = Arrays.asList(tmpDiazepam, tmpBromazepam, tmpZolazepam);
        ScaffoldNetwork tmpDiazepinonesNetwork = tmpScaffoldGen.generateScaffoldNetwork(tmpInputMolecules);
        System.out.println("\nScaffold network:");
        //the root scaffolds have level 0 and from there, the level increases.
        //scaffolds on level 0 either have only one ring or cannot be dissected further, e.g. complex aromatic systems
        System.out.println("Max level: " + tmpDiazepinonesNetwork.getMaxLevel());
        //Printing all scaffold and parent scaffolds on each level
        for (int i = 0; i <= tmpDiazepinonesNetwork.getMaxLevel(); i++) {
            System.out.println("\nLevel " + i + ":");
            for (ScaffoldNodeBase tmpNode: tmpDiazepinonesNetwork.getAllNodesOnLevel(i)) {
                System.out.println(tmpSmiGen.create((IAtomContainer) tmpNode.getMolecule()));
            }
        }
        //the scaffold network graph can be exported as an adjacency matrix
        Integer[][] tmpMatrix = tmpDiazepinonesNetwork.getMatrix();
        System.out.println("\nMatrix:");
        for (int i = 0; i < tmpMatrix.length; i++) {
            String tmpRow = "";
            for (int j = 0; j < tmpMatrix[i].length; j++) {
                tmpRow += tmpMatrix[i][j] + ", ";
            }
            System.out.println(tmpRow);
        }
        //the method getMatrixNodes() returns the scaffolds and parent scaffolds ordered by their positions in the matrix above
        System.out.println("\nMatrix nodes:");
        for (Integer tmpIndex : tmpDiazepinonesNetwork.getMatrixNodes().keySet()) {
            System.out.println(tmpIndex + ": " + tmpSmiGen.create((IAtomContainer) tmpDiazepinonesNetwork.getMatrixNode(tmpIndex).getMolecule()));
            System.out.println("Level: " + tmpDiazepinonesNetwork.getMatrixNode(tmpIndex).getLevel());
        }
        /*
        Output:
        Scaffold network:
        Max level: 2

        Level 0:
        O=C1NC=CC=NC1
        c1ccccc1
        n1ccc[nH]1
        n1ccccc1

        Level 1:
        O=C1NC=CC(=NC1)c2ncccc2
        O=C1Nc2ccccc2C=NC1
        O=C1NC=CC(=NC1)c2ccccc2
        O=C1Nc2[nH]ncc2C=NC1

        Level 2:
        O=C1Nc2[nH]ncc2C(=NC1)c3ccccc3
        O=C1Nc2ccccc2C(=NC1)c3ccccc3
        O=C1Nc2ccccc2C(=NC1)c3ncccc3

        Matrix:
        0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
        1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0,
        1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0,
        0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0,
        0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0,
        0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
        0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,
        0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,

        Matrix nodes:
        0: O=C1Nc2ccccc2C(=NC1)c3ccccc3
        Level: 2
        1: O=C1Nc2ccccc2C=NC1
        Level: 1
        2: O=C1NC=CC(=NC1)c2ccccc2
        Level: 1
        3: O=C1NC=CC=NC1
        Level: 0
        4: c1ccccc1
        Level: 0
        5: O=C1Nc2ccccc2C(=NC1)c3ncccc3
        Level: 2
        6: O=C1NC=CC(=NC1)c2ncccc2
        Level: 1
        7: n1ccccc1
        Level: 0
        8: O=C1Nc2[nH]ncc2C(=NC1)c3ccccc3
        Level: 2
        9: O=C1Nc2[nH]ncc2C=NC1
        Level: 1
        10: n1ccc[nH]1
        Level: 0
        */
    }
    //
    /**
     * Construct a scaffold tree from multiple input molecules (see article for details).
     */
    @Test
    public void testScaffoldTreeGeneration() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpDiazepam = tmpSmiPar.parseSmiles("CN1C(=O)CN=C(C2=C1C=CC(=C2)Cl)C3=CC=CC=C3");
        IAtomContainer tmpBromazepam = tmpSmiPar.parseSmiles("C1C(=O)NC2=C(C=C(C=C2)Br)C(=N1)C3=CC=CC=N3");
        IAtomContainer tmpZolazepam = tmpSmiPar.parseSmiles("CC1=NN(C2=C1C(=NCC(=O)N2C)C3=CC=CC=C3F)C");
        List<IAtomContainer> tmpInputMolecules = Arrays.asList(tmpDiazepam, tmpBromazepam, tmpZolazepam);
        //if the input molecules have different root scaffolds, multiple scaffold trees (a "forest") are created
        // here, only one tree is created
        ScaffoldTree tmpDiazepinonesTree = tmpScaffoldGen.generateSchuffenhauerForest(tmpInputMolecules).get(0);
        System.out.println("\nScaffold tree:");
        //a scaffold tree has only one root node
        TreeNode tmpDiazepinoneTreeRootScaffold = tmpDiazepinonesTree.getRoot();
        System.out.println("Root scaffold of Diazepinone tree: " + tmpSmiGen.create((IAtomContainer) tmpDiazepinoneTreeRootScaffold.getMolecule()));
        //the root scaffold has level 0 and from there, the level increases.
        //root scaffolds on level 0 either have only one ring or cannot be dissected further, e.g. complex aromatic systems
        System.out.println("Max level: " + tmpDiazepinonesTree.getMaxLevel());
        //Printing all scaffold and parent scaffolds on each level
        for (int i = 0; i <= tmpDiazepinonesTree.getMaxLevel(); i++) {
            System.out.println("\nLevel " + i + ":");
            for (ScaffoldNodeBase tmpNode: tmpDiazepinonesTree.getAllNodesOnLevel(i)) {
                System.out.println(tmpSmiGen.create((IAtomContainer) tmpNode.getMolecule()));
            }
        }
        //the scaffold tree graph can be exported as an adjacency matrix
        Integer[][]  tmpMatrix = tmpDiazepinonesTree.getMatrix();
        System.out.println("\nMatrix:");
        for (int i = 0; i < tmpMatrix.length; i++) {
            String tmpRow = "";
            for (int j = 0; j < tmpMatrix[i].length; j++) {
                tmpRow += tmpMatrix[i][j] + ", ";
            }
            System.out.println(tmpRow);
        }
        //the method getMatrixNodes() returns the scaffolds and parent scaffolds ordered by their positions in the matrix above
        System.out.println("\nMatrix nodes:");
        for (Integer tmpIndex : tmpDiazepinonesTree.getMatrixNodes().keySet()) {
            System.out.println(tmpIndex + ": " + tmpSmiGen.create((IAtomContainer) tmpDiazepinonesTree.getMatrixNode(tmpIndex).getMolecule()));
            System.out.println("Level: " + tmpDiazepinonesTree.getMatrixNode(tmpIndex).getLevel());
        }
        /*
        Output:
        Scaffold tree:
        Root scaffold of Diazepinone tree: O=C1NC=CC=NC1
        Max level: 2

        Level 0:
        O=C1NC=CC=NC1

        Level 1:
        O=C1Nc2[nH]ncc2C=NC1
        O=C1Nc2ccccc2C=NC1

        Level 2:
        O=C1Nc2ccccc2C(=NC1)c3ccccc3
        O=C1Nc2[nH]ncc2C(=NC1)c3ccccc3
        O=C1Nc2ccccc2C(=NC1)c3ncccc3

        Matrix:
        0, 1, 0, 0, 1, 0,
        1, 0, 1, 1, 0, 0,
        0, 1, 0, 0, 0, 0,
        0, 1, 0, 0, 0, 0,
        1, 0, 0, 0, 0, 1,
        0, 0, 0, 0, 1, 0,

        Matrix nodes:
        0: O=C1NC=CC=NC1
        Level: 0
        1: O=C1Nc2ccccc2C=NC1
        Level: 1
        2: O=C1Nc2ccccc2C(=NC1)c3ccccc3
        Level: 2
        3: O=C1Nc2ccccc2C(=NC1)c3ncccc3
        Level: 2
        4: O=C1Nc2[nH]ncc2C=NC1
        Level: 1
        5: O=C1Nc2[nH]ncc2C(=NC1)c3ccccc3
        Level: 2
        */
    }
    //
    /**
     * Example analyses of the generated tree or network graphs (see article for details).
     */
    @Test
    public void testNetworkAndTreeAnalysis() throws Exception {
        ScaffoldGenerator tmpScaffoldGen = new ScaffoldGenerator();
        SmilesParser tmpSmiPar = new SmilesParser(SilentChemObjectBuilder.getInstance());
        SmilesGenerator tmpSmiGen = tmpScaffoldGen.getSmilesGenerator();
        IAtomContainer tmpDiazepam = tmpSmiPar.parseSmiles("CN1C(=O)CN=C(C2=C1C=CC(=C2)Cl)C3=CC=CC=C3");
        IAtomContainer tmpBromazepam = tmpSmiPar.parseSmiles("C1C(=O)NC2=C(C=C(C=C2)Br)C(=N1)C3=CC=CC=N3");
        IAtomContainer tmpZolazepam = tmpSmiPar.parseSmiles("CC1=NN(C2=C1C(=NCC(=O)N2C)C3=CC=CC=C3F)C");
        List<IAtomContainer> tmpInputMolecules = Arrays.asList(tmpDiazepam, tmpBromazepam, tmpZolazepam);
        ScaffoldNetwork tmpDiazepinonesNetwork = tmpScaffoldGen.generateScaffoldNetwork(tmpInputMolecules);
        ScaffoldTree tmpDiazepinonesTree = tmpScaffoldGen.generateSchuffenhauerForest(tmpInputMolecules).get(0);
        IAtomContainer tmpBenzene = tmpSmiPar.parseSmiles("c1ccccc1");
        //false, the Benzene parent scaffold is not prioritised according to the scaffold tree rules
        System.out.println("\nDiazepinones tree contains benzene: " + tmpDiazepinonesTree.containsMolecule(tmpBenzene));
        //true, scaffold network contains all possible parent scaffolds
        System.out.println("\nDiazepinones network contains benzene: " + tmpDiazepinonesNetwork.containsMolecule(tmpBenzene));
        NetworkNode tmpBenzeneNetworkNode = (NetworkNode) tmpDiazepinonesNetwork.getNode(tmpBenzene);
        System.out.println("Level of Benzene node: " + tmpBenzeneNetworkNode.getLevel());
        System.out.println("Benzene is a root node in the network (\"orphan\", does not have parents): " + tmpBenzeneNetworkNode.isOrphan());
        System.out.println("Benzene is a leaf node in the network (does not have children): " + tmpBenzeneNetworkNode.isLeaf());
        System.out.println("Direct children of Benzene network node:");
        for (Object tmpParentNode : tmpBenzeneNetworkNode.getChildren()) {
            System.out.println(tmpSmiGen.create((IAtomContainer)((NetworkNode)tmpParentNode).getMolecule()));
        }
        //origin: input molecules that possess the respective scaffold, or it is generated as a parent scaffold of their scaffold
        System.out.println("Number of origin molecules of Benzene: " + tmpBenzeneNetworkNode.getOriginCount());
        System.out.println("List of origins as SMILES strings:");
        List<String> tmpOriginSmiles = tmpBenzeneNetworkNode.getOriginSmilesList();
        for (String tmpOriginSmilesString : tmpOriginSmiles) {
            System.out.println(tmpOriginSmilesString);
        }
        //virtual origin: the scaffold was only created as a parent scaffold for the respective input molecule
        //non-virtual origin: the scaffold was the primary extracted scaffold of the input molecule (i.e. extracted using getScaffold())
        System.out.println("Benzene has non-virtual origin molecules in the network: " + tmpBenzeneNetworkNode.hasNonVirtualOriginSmiles());
        System.out.println("Non-virtual origin count: " + tmpBenzeneNetworkNode.getNonVirtualOriginCount());
        //empty list, no benzene derivative was among the input molecules, the scaffold was only generated as a parent scaffold
        System.out.println("List of non-virtual origins as SMILES strings:");
        List<String> tmpNonVirtualOriginSmiles = tmpBenzeneNetworkNode.getNonVirtualOriginSmilesList();
        for (String tmpNonVirtualOriginSmilesString : tmpNonVirtualOriginSmiles) {
            System.out.println(tmpNonVirtualOriginSmilesString);
        }
        //following analysis produces the three input molecules and their scaffolds in this example because they all had the same number of rings.
        // this way, there are no scaffolds on lower levels that have non-virtual origins
        System.out.println("\nSearching for scaffolds with non-virtual origins in the network: ");
        for (ScaffoldNodeBase tmpNode : tmpDiazepinonesNetwork.getAllNodes()) {
            if (tmpNode.hasNonVirtualOriginSmiles()) {
                System.out.println("\nNetwork scaffold: " + tmpSmiGen.create((IAtomContainer) tmpNode.getMolecule()));
                System.out.println("Non-virtual origin molecules: ");
                for (Object tmpSMILES : tmpNode.getNonVirtualOriginSmilesList()) {
                    System.out.println(tmpSMILES);
                }
            }
        }
        /*
        Output:
        Diazepinones tree contains benzene: false

        Diazepinones network contains benzene: true
        Level of Benzene node: 0
        Benzene is a root node in the network ("orphan", does not have parents): true
        Benzene is a leaf node in the network (does not have children): false
        Direct children of Benzene network node:
        O=C1Nc2ccccc2C=NC1
        O=C1NC=CC(=NC1)c2ccccc2
        Number of origin molecules of Benzene: 3
        List of origins as SMILES strings:
        O=C1N(C=2C=CC(Cl)=CC2C(=NC1)C3=CC=CC=C3)C
        O=C1NC=2C=CC(Br)=CC2C(=NC1)C=3N=CC=CC3
        O=C1N(C2=C(C(=NC1)C3=CC=CC=C3F)C(=NN2C)C)C
        Benzene has non-virtual origin molecules in the network: false
        Non-virtual origin count: 0
        List of non-virtual origins as SMILES strings:

        Searching for scaffolds with non-virtual origins in the network:

        Network scaffold: O=C1Nc2ccccc2C(=NC1)c3ccccc3
        Non-virtual origin molecules:
        O=C1N(C=2C=CC(Cl)=CC2C(=NC1)C3=CC=CC=C3)C

        Network scaffold: O=C1Nc2ccccc2C(=NC1)c3ncccc3
        Non-virtual origin molecules:
        O=C1NC=2C=CC(Br)=CC2C(=NC1)C=3N=CC=CC3

        Network scaffold: O=C1Nc2[nH]ncc2C(=NC1)c3ccccc3
        Non-virtual origin molecules:
        O=C1N(C2=C(C(=NC1)C3=CC=CC=C3F)C(=NN2C)C)C
        */
    }
}
