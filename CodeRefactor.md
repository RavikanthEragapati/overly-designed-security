# Code Refactor suggestions

@UtilityClass - This Lombok annotation which is still in Experimental state,
once approved and moved to prod, the following change need to be applied on constant file.
1. Add @UtilityClass annotation on class
2. Remove @NoArgsConstructor(access = AccessLevel.PRIVATE) annotation on class
3. Remove static keyword on all the fields and methods