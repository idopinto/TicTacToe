/**
 * factory (design pattern) for building new player.
 */
public class PlayerFactory {

    /**
     * constructor
     *
     * @param arg player description
     * @return new player instance.
     */
    Player buildPlayer(String arg) {
        switch (arg) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "clever":
                return new CleverPlayer();
            case "snartypamts":
                return new SnartypamtsPlayer();
            default:
                return null;
        }
    }
}
