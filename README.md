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
JEnigma is a CLI-based application which emulating work of encryption machine [Enigma](https://en.wikipedia.org/wiki/Enigma_machine).
With help of JEnigma the text that you want to encrypt would be encoded in a same way if you would use the **real enigma** instead.
It has feature to "build" enigma from the **parts that exists in real life**. Or you can generate **random parts of enigma**
with random state for your further encryption.

All application logic written in Java. It's uses [Picocli](https://picocli.info/) framework for work with command line.

---

## Build and run

- Clone repo
```bash
git clone https://github.com/ex-neskoro/JEnigma
```
- Go to project directory and build .jar file with Gradle
```bash
./gradlew jar
```
- Now you can start the app with Java 17
```bash
java -jar JEnigma/build/libs/JEnigma-1.0.jar <commands> <options>
```

In that way you should specify the JEnigma.jar every time when you want to run the app.

For **true** 🧐 CLI experience you can go further and compile whole app to the only one executable file with [GraalVM](https://www.graalvm.org/) native image.

- [Download](https://github.com/graalvm/graalvm-ce-builds/releases) and [install](https://www.graalvm.org/latest/docs/getting-started/) GraalVM JDK for Java 17
- Install native-image for GraalVM
```bash
gu install native-image 
```
- And build executable native image. It create executable platform-specific file **jenigma** 
```bash
native-image -jar JEnigma/build/libs/JEnigma-1.0.jar jenigma
```
- Now you can use that file instead of .jar for working with app
```bash
./jenigma setup random -r 4 -l en
```

Go to [official documentation](https://www.graalvm.org/22.0/reference-manual/native-image/) for more info about GraalMV native image.

---

## How to use

### Setup enigma
You **must setup enigma state first** to encode some text.

There is two ways for setup enigma:

- With random parts and state

```bash
./jenigma setup random [-l=<languageAlphabet>] [-r=<rotorsCount>] 
```

- With standard **real-life** parts 

```bash
./jenigma setup standard [-c=<commutatorState>] -er=<entryRotorType> -ref=<reflectorType> -sr=<rotorTypeList>
```

This commands output text with enigma state in stdout - you **should** save it to the file to use it in next command.
Example:
```bash
./jenigma setup random > ~/enigma_state.txt
```

### Run encoding
With enigma state file you can encode text. Input file with text and file with enigma state **are required**.
```bash
./jenigma run -in=<fileInput> [-out=<fileOut>] -s=<fileState>
```
This command encode text from input file and return it to stdout by default.

### Run decoding
To decode text you use the same command that are using to encode - it works this way because of enigma machine algorithm.

You **must** use the encoded text and **the same enigma_state.file** which was used in encoding process - again, because of enigma algorithm 🧐
```bash
./jenigma run -in encoded.txt -s the_same_enigma_state.txt > decoded.txt
```

### Help command
Every command and subcommand in enigma has -help option - it contains description about current command, it's subcommands and all options.
Feel free to use it 😁
```bash
./jenigma setup -help
./jenigma run -help
```

---

## References

In my work for this project I did use several sources:

- [Enigma rotor details](https://en.wikipedia.org/wiki/Enigma_rotor_details) article on wikipedia
- [Enigma wiring](https://www.cryptomuseum.com/crypto/enigma/wiring.htm) article on [cryptomuseum.com](https://www.cryptomuseum.com/) - really help a lot to understand all parts of enigma machine
- [158,962,555,217,826,360,000 (Enigma Machine) - Numberphile](https://www.youtube.com/watch?v=G2_Q9FoD-oQ&t=0s) and [Flaw in the Enigma Code - Numberphile](https://www.youtube.com/watch?v=V4V2bpZlqx8&t=0s)
interesting videos about how the enigma works, about inner parts and it's history

If you want to get better understanding of Enigma and it algorithm, you can check this courses.