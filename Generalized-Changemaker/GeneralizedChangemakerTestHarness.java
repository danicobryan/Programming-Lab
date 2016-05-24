public class GeneralizedChangemakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test1();
        test1Reversed();
        test_USA();
        notGreedy();
        toalTest();
        exampleProblem();
        // Add more!

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        int[] usaDenominations = new int[] { 25, 10, 5, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 99);
        try {
            displaySuccessIfTrue(3 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test1() {
        int[] usaDenominations = new int[] { 1, 4, 9 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 12);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(3 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }  
    }

    public static void test1Reversed() {
        int[] usaDenominations = new int[] { 9, 4, 1 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 12);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(3 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }  
    }


    public static void notGreedy() {
        int[] usaDenominations = new int[] { 15, 1, 25 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 30);
        try {
            displaySuccessIfTrue(2 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void toalTest() {
        int[] usaDenominations = new int[] { 7395, 608, 17736, 24022, 95877623 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 35472);
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
        try {
            displaySuccessIfTrue(0 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
        try {
            displaySuccessIfTrue(0 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void exampleProblem() {
        int[] usaDenominations = new int[] { 3, 1, 9, 8 };

        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 14);
        try {
            displaySuccessIfTrue(2 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}
