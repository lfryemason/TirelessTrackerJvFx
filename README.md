# TirelessTracker

## Liam Frye-Mason

#### A Java and JavaFX application that keeps track of MTG matches and provides statistics.

GUI application that allows you to add, edit and delete "matches" specifically for MTG, but can be for any game. Displays overall statistics and selected match information on the right panel with the full tree of matches in the center. Opening up the matchup details gives a list of all matches with the specified user deck and opponent deck and general statistics on that list.

Currently loads and saves to \res\data\matches.json on application open and exit. Currently contains my match data from the past few months of playing at weekly Modern events in Albuquerque.

## Structures

Objects and Structures:
#### MatchData

This contains all information needed for a single match. Result information is held in a string that is parsed or created with static methods in MatcchData.java.

#### MatchupName

Just includes the user deck name and the opponent deck name with getters, an equals function and a hashing function for MatchList.

#### Stat

Contains all information needed for statistics on a given list. Can be generated by giving it a list or by adding matches individually. Does not contain MatchData, only result information such as total number of games/matches won.

#### MatchList

Includes:
* A list of matches that is displayed
* A map of MatchupNames to lists of MatchData. This is used to quickly find matchups for matchup details.
* Stat for overall statistics to be displayed in the right panel.

## Plans for the future
* Saving and loading from specified files.
* Add buttons to top menu such as save and load to/from file, add/edit/delete match, etc.
* Add search functionality to find matchups easily.
* More unit tests for better coverage.
* Histograms of matches played/win percentage/matches won over months.