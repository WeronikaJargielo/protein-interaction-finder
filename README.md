[![Version](http://img.shields.io/badge/version-1.0.1-blue.svg?style=flat)](https://search.maven.org/artifact/io.github.WeronikaJargielo/protein-interaction-finder/1.0.1/jar)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/WeronikaJargielo/protein-interaction-finder/blob/master/LICENSE)


# Protein Interaction Finder

ProteinInteractionFinder (PIF) is a Java 11 library for determining interatomic interactions in protein structures, obtained from PDB (Protein Data Bank) files.

Proteins play a huge role in the functioning of all living organisms.
The set of interatomic interactions found in proteins has a great impact on controlling their spatial structures and dynamics. Moreover, it can provide scientists with information useful in determining the structures of native proteins.

PIF is a library for Java, allowing for the identification of selected interatomic interactions (hydrogen and disulfide bonds, ionic, hydrophobic, aromatic-aromatic, sulphur-aromatic and amino-aromatic interactions) in the three-dimensional protein structure, obtained from a PDB file.

The interaction identification rules applied in the PIF library are based on well-documented theoretical fundamentals, gathered from experimental studies of interactions in native protein structures.
An illustrative analysis of the time required for the identification of interatomic interactions shows that the implemented algorithms feature linear scalability.

For parsing PDB files and performing some of the calculations BioJava v6.0.0-alpha2 library was used.


### Maven Repository
To ensure convenient application in any Java project, ProteinInteractionFinder releases are published to Maven Central Repository.


### Quick Installation

If you are using Maven you can add the dependency to ProteinInteractionFinder by adding the following XML code to your project's pom.xml file:

```xml
    <dependencies>
        <dependency>
            <groupId>io.github.WeronikaJargielo</groupId>
            <artifactId>protein-interaction-finder</artifactId>
            <version>1.0.1</version>
        </dependency>
    <!-- other dependencies -->
    </dependencies>
```


### Documentation
Public API documentation is available [here](https://weronikajargielo.github.io/protein-interaction-finder/).


### Example

Basic usage example is presented in examples/Main.java file.
