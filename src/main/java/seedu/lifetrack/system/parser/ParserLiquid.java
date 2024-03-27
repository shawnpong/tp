package seedu.lifetrack.system.parser;

import seedu.lifetrack.Entry;
import seedu.lifetrack.liquids.liquidlist.LiquidEntry;
import seedu.lifetrack.system.exceptions.InvalidInputException;
import seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage;

public class ParserLiquid {

    /**
     * Parses a string input to create a Liquid object representing liquid intake.
     *
     * This method expects the input string to follow a specific format, where the
     * beverage name and quantity are separated by the delimiters 'b/' and 'v/'.
     * The method extracts these components and creates a Liquid object.
     * If any part of the input is missing or empty, an InvalidInputException is thrown.
     *
     * @param input the input string to be parsed, containing beverage name and quantity
     * @return a Liquid object representing liquid intake
     * @throws InvalidInputException if the input string is missing components or
     *                              contains empty fields
     */
    public static Entry parseLiquidInput(String input) throws InvalidInputException {
        //get index for b/ and  v/ inputs
        int beverageIndex = input.indexOf("b/");
        int volumeIndex = input.indexOf("v/");

        // Handle exception when b/ or v/ not entered
        if (beverageIndex == -1 || volumeIndex == -1) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationMissingKeywordMessage());
        }

        assert (beverageIndex != -1 || volumeIndex != -1) : "ensures that beverage and volume has been keyed in";

        //Handle exception when order of b/ and v/ is incorrect
        if (volumeIndex < beverageIndex) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationIncorrectOrderMessage());
        }

        // splits string according to b/ and v/ keywords
        String[] parts = input.split("b/|v/");

        //ensures that v/DESCRIPTION is not empty
        if (parts.length < 3) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationEmptyDescriptionMessage());
        }

        // extracts beverage name and quantity portion from input
        String beverageName = parts[1].trim();
        String strVolume = parts[2].trim();

        // ensures that both inputs are not empty
        if (beverageName.isEmpty() || strVolume.isEmpty()) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationEmptyDescriptionMessage());
        }

        int volume;
        // Handle exception when non integer values are keyed in for volume
        try {
            volume = Integer.parseInt(strVolume);
        } catch(NumberFormatException e) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationNonIntegerVolumeMessage());
        }

        // Handle exception when negative values are keyed in for volume
        if (volume <= 0) {
            throw new InvalidInputException(InvalidInputExceptionMessage.getHydrationNonIntegerVolumeMessage());
        }
        return getNewLiquidEntry(volume, beverageName);
    }

    private static Entry getNewLiquidEntry(int volume, String name) throws InvalidInputException {
        //create objects for Beverage

        //create Object Entry to be returned
        return new LiquidEntry(name, volume, "111111");
    }
}
