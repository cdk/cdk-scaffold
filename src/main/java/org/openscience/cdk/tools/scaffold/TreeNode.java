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

import java.util.Objects;

/**
 * The TreeNodes are nodes from which a {@link ScaffoldTree} can be built.
 * It is used to organise the IAtomContainers and enables a relationship between the different objects.
 * A TreeNode can have different children but only one parent.
 * The parents are the smaller parent scaffolds.
 *
 * @param <MoleculeType> As MoleculeType, any data type can be defined.
 *                      In our scenario, the node contains a CDK IAtomContainer.
 *
 * @author Julian Zander, Jonas Schaub (zanderjulian@gmx.de, jonas.schaub@uni-jena.de)
 * @version 1.0.0.0
 */
public class TreeNode<MoleculeType> extends ScaffoldNodeBase<MoleculeType> {
    /**
     * Parent of the node
     */
    private TreeNode<MoleculeType> parent;

    /**
     * Constructor
     * @param aMolecule molecule of the TreeNode
     */
    public TreeNode(MoleculeType aMolecule) {
        super(aMolecule);
    }

    /**
     * Shows if the node has parents. Description copied from {@link ScaffoldNodeBase}.
     * @return Whether the node has parents
     */
    @Override
    public boolean isOrphan() {
        return parent == null;
    }

    /**
     * Adds a child to the tree node, i.e. links it to a node on the level below.
     * Description copied from {@link ScaffoldNodeBase}.
     * @param aMolecule Molecule of the child
     * @return Node of the child
     * @throws NullPointerException if parameter is null
     */
    @Override
    public TreeNode<MoleculeType> addChild(MoleculeType aMolecule) throws NullPointerException {
        Objects.requireNonNull(aMolecule, "Given molecule is 'null'");
        TreeNode<MoleculeType> tmpChildNode = new TreeNode<>(aMolecule);
        this.children.add(tmpChildNode);
        tmpChildNode.parent = this;
        return tmpChildNode;
    }

    /**
     * Outputs the level on which the node is located in the graph structure.
     * The level indicates the distance to the root (node without parents) and
     * is determined by setting the level of the parent node + 1. The root itself has the level 0.
     * The level is therefore dependent on the data structure and does not have to be set.
     * Description copied from {@link ScaffoldNodeBase}.
     * @return level of the node in the entire node collection
     */
    @Override
    public int getLevel() {
        if (this.isOrphan())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    /**
     * Get the parent node.
     * @return parent node
     */
    public TreeNode<MoleculeType> getParent() {
        return this.parent;
    }

    /**
     * Set the parent node.
     * @param aParent parent that are set
     * @throws NullPointerException if parameter is null
     */
    public void setParent(TreeNode<MoleculeType> aParent) throws NullPointerException {
        Objects.requireNonNull(aParent, "Given TreeNode is 'null'");
        this.parent = aParent;
    }
}