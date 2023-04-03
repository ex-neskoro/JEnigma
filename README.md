# JEnigma

---

<!-- TOC -->
* [JEnigma](#jenigma)
  * [Description](#description)
  * [Build and run](#build-and-run)
  * [How to use](#how-to-use)
    * [Setup enigma](#setup-enigma)
    * [Run encoding](#run-encoding)
    * [Run decoding](#run-decoding)
    * [Help command](#help-command)
  * [References](#references)
<!-- TOC -->

## Description
JEnigma is a CLI-based application that emulates the work of an encryption machine [Enigma](https://en.wikipedia.org/wiki/Enigma_machine).
With help of JEnigma the text that you want to encrypt would be encoded in the same way if you would use the **real enigma** instead.
It has the feature to "build" an enigma from the **parts that exist in real life**. Or you can generate **random parts of enigma**
with the random state for your further encryption.

All application logic is written in Java. It uses [Picocli](https://picocli.info/) framework for work with a command line.

---

## Build and run

- Clone repo
```bash
git clone https://github.com/ex-neskoro/JEnigma
```
- Go to the project directory and build .jar file with Gradle
```bash
./gradlew jar
```
- Now you can start the app with Java 17
```bash
java -jar JEnigma/build/libs/JEnigma-1.0.jar <commands> <options>
```

In that way, you should specify the JEnigma.jar whenever you want to run the app.

For **true** 🧐 CLI experience you can go further and compile the whole app to only one executable file with [GraalVM](https://www.graalvm.org/) native image.

- [Download](https://github.com/graalvm/graalvm-ce-builds/releases) and [install](https://www.graalvm.org/latest/docs/getting-started/) GraalVM JDK for Java 17
- Install native-image for GraalVM
```bash
gu install native-image 
```
- And build an executable native image. It creates an executable platform-specific file **jenigma**
```bash
native-image -jar JEnigma/build/libs/JEnigma-1.0.jar jenigma
```
- Now you can use that file instead of .jar for working with the app
```bash
./jenigma setup random -r 4 -l en
```

Go to [official documentation](https://www.graalvm.org/22.0/reference-manual/native-image/) for more info about GraalMV native image.

---

## How to use

### Setup enigma
You **must set up an enigma state first** to encode some text.

There are two ways to set up an enigma:

- With random parts and state

```bash
./jenigma setup random [-l=<languageAlphabet>] [-r=<rotorsCount>] 
```

- With standard **real-life** parts

```bash
./jenigma setup standard [-c=<commutatorState>] -er=<entryRotorType> -ref=<reflectorType> -sr=<rotorTypeList>
```

This commands output text with an enigma state in stdout - you **should** save it to the file to use it in the next command.
Example:
```bash
./jenigma setup random > ~/enigma_state.txt
```

### Run encoding
With an enigma state file, you can encode text. Input file with text and file with enigma state **is required**.
```bash
./jenigma run -in=<fileInput> [-out=<fileOut>] -s=<fileState>
```
This command encodes text from the input file and returns it to stdout by default.

### Run decoding
To decode text you use the same command that was used to encode - it works this way because of the enigma machine algorithm.

You **must** use the encoded text and **the same enigma_state.file** which was used in the encoding process - again, because of the enigma algorithm 🧐
```bash
./jenigma run -in encoded.txt -s the_same_enigma_state.txt > decoded.txt
```

### Help command
Every command and subcommand in enigma has -help option - it contains a description of the current command, it's subcommands, and all options.
Feel free to use it 😁
```bash
./jenigma setup -help
./jenigma run -help
```

---

## References

In my work for this project, I did use several sources:

- [Enigma rotor details](https://en.wikipedia.org/wiki/Enigma_rotor_details) article on Wikipedia
- [Enigma wiring](https://www.cryptomuseum.com/crypto/enigma/wiring.htm) article on [cryptomuseum.com](https://www.cryptomuseum.com/) - really helps a lot to understand all parts of the enigma machine
- [158,962,555,217,826,360,000 (Enigma Machine) - Numberphile](https://www.youtube.com/watch?v=G2_Q9FoD-oQ&t=0s) and [Flaw in the Enigma Code - Numberphile](https://www.youtube.com/watch?v=V4V2bpZlqx8&t=0s)
  interesting videos about how the enigma works, about its inner parts and its history

If you want to get a better understanding of Enigma and its algorithm, you can check these sources.