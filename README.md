[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=SE2-Gwent-SS23_Gwent&metric=coverage)](https://sonarcloud.io/summary/new_code?id=SE2-Gwent-SS23_Gwent)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=SE2-Gwent-SS23_Gwent&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=SE2-Gwent-SS23_Gwent)

[![itch.io-Page](https://cdn.jim-nielsen.com/ios/512/gwent-the-witcher-card-game-2019-12-11.png)](https://moritzmusel.itch.io/gwent-ss23)

<img src="https://cdn.vox-cdn.com/thumbor/UY9qkeeHR4j2OSxBma-pqd2VvgU=/0x0:1024x659/1200x800/filters:focal(431x249:593x411)/cdn.vox-cdn.com/uploads/chorus_image/image/49801529/gwentcardgame.0.jpg" width="250" style="display: block;margin-left: auto;margin-right: auto;"/>

# Project description
This repository is an android version of the card game "Gwent" from the famous "The Witcher" game series developed by cdprojektred.  
The implementation does not reflect the entire original game, but instead is a stripped down version with less features. Simultaneously, its easier to learn.

## Gwent  
Official game description:
 > GWENT is a card game of choices and consequences, where skill, not luck, is your greatest weapon. Pick a faction, build an army, and wage war against other players across multiple game modes. With hundreds of cards to collect _charismatic heroes, mighty spells and special abilities_ new strategies are always a thought away.
 
Gwent has two players battleing, in a best of 3, for the win. Win a round by scoring more total points in comparison to your opponent.

# How to play it

Players compete with their decks in up to three rounds. They take turns placing cards from their hand on the board, with the goal of having the most points at the end of the round. Between rounds, cards can be drawn and discarded. The player who wins two rounds wins Gwent.

## Rules
At the start of a match, each player draws 10 cards from their deck. Before the match begins, players may choose to return up to three cards in order to redraw. 
The maximum hand size is 10, any cards that are drawn whilst at this limit will be discarded. If you were to draw cards at the start of the next round (i.e drawing 3 cards at the beginning of round 2) but you are already at the max hand size of 10 then these draws will be converted into extra mulligans.
- Whoever wins the previous Round will start the next Round.
- At the beginning of the second Round, each player draws three random cards from their deck.
- If a third Round is needed, each player draws three more random cards from their deck prior to beginning.
- Players can mulligan 3 card from their hand at the start of any Round.

## The Basics
A single turn consists of playing a card, using a Leader ability, or passing. 
Passing means you are done for the remainder of that battle and will not make any more plays. 
If both players pass, the battle ends and all units on the battlefield are sent to the Graveyard.
Passing is accomplished by holding the left mouse button over the "coin".

The field is made up of two rows: Melee and Ranged.

Players just drag and drop the card to the place (lane) in the selected row. Not completing the drag and drop will cancel the selection.

While on the Battlefield, units add their Strength values to the player's total score. At the end of the battle, the player with the highest total score wins. If both players have the same score at the end of a battle, both players lose.

Strength is tracked per row, and then combined in a number to the left of the "crowns".
If a card's strength is reduced to zero, it is sent to the player's graveyard and its strength is removed from the player's total.

The player that wins two out of three battles wins the match.
