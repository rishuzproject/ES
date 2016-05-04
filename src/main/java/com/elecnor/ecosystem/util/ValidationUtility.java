package com.elecnor.ecosystem.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/* 1- @NotBlank	CharSequence	Checks that the annotated character sequence is not null and the trimmed length is greater than 0. The difference to @NotEmpty is that this constraint can only be applied on strings and that trailing whitespaces are ignored.	None
 2- @NotEmpty	CharSequence, Collection, Map and arrays	Checks whether the annotated element is not null nor empty	None
 3- @Range(min=, max=)	BigDecimal, BigInteger, CharSequence, byte, short, int, long and the respective wrappers of the primitive types
 4- @CreditCardNumber(ignoreNonDigitCharacters=)	CharSequence	Checks that the annotated character sequence passes the Luhn checksum test. Note, this validation aims to check for user mistakes, not credit card validity! See also Anatomy of Credit Card Numbers. ignoreNonDigitCharacters allows to ignore non digit characters. The default is false.	None
 5- @EAN	CharSequence	Checks that the annotated character sequence is a valid EAN barcode. type determines the type of barcode. The default is EAN-13.	None
 6- @Email	CharSequence	Checks whether the specified character sequence is a valid email address. The optional parameters regexp and flags allow to specify an additional regular expression (including regular expression flags) which the email must match.	None
 7- @Length(min=, max=)	CharSequence	Validates that the annotated character sequence is between min and max included	Column length will be set to max
 8- @AssertFalse	Boolean, boolean	Checks that the annotated element is false	None
 9- @AssertTrue	Boolean, boolean	Checks that the annotated element is true	None
 10- @DecimalMax(value=, inclusive=)	BigDecimal, BigInteger, CharSequence, byte, short, int, long and the respective wrappers of the primitive types; Additionally supported by HV: any sub-type of Number	Checks whether the annotated value is less than the specified maximum, when inclusive=false. Otherwise whether the value is less than or equal to the specified maximum. The parameter value is the string representation of the max value according to the BigDecimal string representation.	None
 11- @DecimalMin(value=, inclusive=)	BigDecimal, BigInteger, CharSequence, byte, short, int, long and the respective wrappers of the primitive types; Additionally supported by HV: any sub-type of Number	Checks whether the annotated value is larger than the specified minimum, when inclusive=false. Otherwise whether the value is larger than or equal to the specified minimum. The parameter value is the string representation of the min value according to the BigDecimal string representation.	None
 12- @Digits(integer=, fraction=)	BigDecimal, BigInteger, CharSequence, byte, short, int, long and the respective wrappers of the primitive types; Additionally supported by HV: any sub-type of Number	Checks whether the annoted value is a number having up to integer digits and fraction fractional digits	Defines column precision and scale
 13- @Future	java.util.Date, java.util.Calendar; Additionally supported by HV, if the Joda Time date/time API is on the class path: any implementations of ReadablePartial and ReadableInstant	Checks whether the annotated date is in the future	None
 14- @Max(value=)	BigDecimal, BigInteger, byte, short, int, long and the respective wrappers of the primitive types; Additionally supported by HV: any sub-type of CharSequence (the numeric value represented by the character sequence is evaluated), any sub-type of Number	Checks whether the annotated value is less than or equal to the specified maximum	Adds a check constraint on the column
 15- @Min(value=)	BigDecimal, BigInteger, byte, short, int, long and the respective wrappers of the primitive types; Additionally supported by HV: any sub-type of CharSequence (the numeric value represented by the char sequence is evaluated), any sub-type of Number	Checks whether the annotated value is higher than or equal to the specified minimum	Adds a check constraint on the column
 16- @NotNull	Any type	Checks that the annotated value is not null.	Column(s) are not nullable
 17- @Null	Any type	Checks that the annotated value is null	None
 18- @Past	java.util.Date, java.util.Calendar; Additionally supported by HV, if the Joda Time date/time API is on the class path: any implementations of ReadablePartial and ReadableInstant	Checks whether the annotated date is in the past	None
 19- @Pattern(regex=, flag=)	CharSequence	Checks if the annotated string matches the regular expression regex considering the given flag match	None
 20- @Size(min=, max=)	CharSequence, Collection, Map and arrays	Checks if the annotated element's size is between min and max (inclusive)	Column length will be set to max
 21- @Valid	Any non-primitive type	Performs validation recursively on the associated object. If the object is a collection or an array, the elements are validated recursively. If the object is a map, the value elements are validated recursively.	None
 */

public class ValidationUtility {

	private static Validator validator = null;

	public static Validator initialize() {
		if (validator == null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
		}
		return validator;
	}
	
	public String writeErrorsInExcelFormat(String dataToWrite) throws Exception {
		System.out.println("hererer:"+dataToWrite);
		return null;
	}
}
