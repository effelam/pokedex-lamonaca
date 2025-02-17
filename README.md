# POKEDEX APP

## How to run it
This app is written in Java17. In order to run it please, download the latest JDK17 from https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html (or the latest JDK from https://www.oracle.com/it/java/technologies/downloads/).

Either you can: 
 - clone repository
 - download directly .jar file from https://github.com/effelam/pokedex-lamonaca/blob/9719d733eaacca9f4e6e4c91534be53676d1f3b8/build/libs/pokedex-lamonaca-0.0.1-SNAPSHOT.jar

From terminal run java -jar .\pokedex-lamonaca-0.0.1-SNAPSHOT.jar from pokedexlamonaca/build/libs if you have cloned repository or from folder where you downloaded the .jar file.

App listens to 8080 port, so please make sure it is open to use. You can go to:
- http://localhost:8080/pokemon/raichu for Pokemon normal info
- http://localhost:8080/pokemon/raichu for Pokemon info with the fun translation logic

Feel free to change Pokemon name at the end of url in order to have other Pokemon info.

## Some considerations
There are some design choices that I would like to discuss as in such a limited application domain they fail to show the 
same potential that they could have in a production environment. I highlight some of them class by class.

### PokeApiRemoteClient.class
This class is responsible to call PokeApi and return the proper response. There are some design choice that I want to discuss.

First is that I wrote response classes putting only the minimal set of information that I needed for this project. So,
for example, I did not put types, moves or abilities in the PokemonBasicInfo subclass. This was done in order to not overflow
code with useless piece of data but in a production application I think that all those data should be there because
users could want them too.

Second point, in PokeApiRemoteClient (and in the FunTranslatorRemoteClient) class there is a static string that
represent the base path of PokeApi apis (Funtranslations base path for the other client). For now that string is used only once
but in production we could implement other api from the same base path and so putting it as a static resource would reduce
repetitions.

As third and final point there is a different behaviour between getPokemonBasicInfo and getPokemonDetails functions. First function
takes as an input pokemonName (and use it as a pathVariable) on the contrary getPokemonDetails takes as an input a full url.
There were two different ways to implement this. The other one was to make it take as an input only the PokemonId (returned
by getPokemonBasicInfo) and make getPokemonDetails build the url using the base PokeApi path. This second approach could make
the function more flexible (you don't need full url but only the Pokemon id) but if for some reason PokeApi decides to change
that api url we need to change it. For now I decided to be more robust against api changes but the more flexible one is a
nice solution too.

### FindTranslationNameHelper
This project requirements was that for the Pokedex entry translation we should use the Yoda one if the Pokemon is legendary
or its habitat is a cave, Shakespeare one otherwise. Obviously for a requirement this simple an if-else statement is sufficient
but in a production environment there could be way more habitat-translation couple. In order to make code cleaner and more
maintainable a map habitat - translation is introduced and an helper class read it and return the proper value (or the default
one if no translation is found). Adding a new habitat-translation couple means only adding that couple to map builder, without
adding new if statement to code.

### PokemonInfoFactory
PokeApi https://pokeapi.co/api/v2/pokemon-species/{pokemon-id} returns a list of flavor_text_entries for each game where
that Pokemon is present and for each supported language. There were no specifications about which entry to return, so for simplicity
I return the first english one. Getting one from a specific game is trivial if needed.