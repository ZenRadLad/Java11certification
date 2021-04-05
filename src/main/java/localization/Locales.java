package localization;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Locales {

	private static final Locale ukLocale = new Locale("en", "GB");
	private static final Locale usLocale = new Locale("en", "US");
	private static final Locale frLocale = Locale.FRANCE;

	private static final String I18N_KEY = "i18n";
	private static final String APP_TITLE_KEY = "app.title";
	private static final String APP_HELLO_KEY = "app.hello";

	public static void main(String[] args) throws ParseException {
		locale();
		localeAndNumericValues();
		resourceBundle();
	}

	private static void locale() {
		Locale current = Locale.getDefault();

		System.out.println("********** Locales ********");
		System.out.println("Locale France : " + usLocale);
		System.out.println("Locale US : " + usLocale);
		System.out.println("Locale England : " + ukLocale);
		System.out.println("Locale current : " + current);
	}

	private static void localeAndNumericValues() throws ParseException {
		BigDecimal price = BigDecimal.valueOf(9.99);
		Double tax = 0.3;
		int quantity = 70;

		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(ukLocale);
		NumberFormat percentageFormat = NumberFormat.getPercentInstance(ukLocale);
		NumberFormat numberFormat = NumberFormat.getNumberInstance(frLocale);

		System.out.println("********** Fromatting Numerics using Locales ********");
		System.out.println("Fromatting uk currency using ukLocale : " + currencyFormat.format(price));
		System.out.println("Fromatting fr percentage using frLocale : " + percentageFormat.format(tax));
		System.out.println("Fromatting number using ukLocale : " + numberFormat.format(quantity));

		Double formattedPrice = (Double) currencyFormat.parse("£17.25");
		Double formattedTax = (Double) percentageFormat.parse("41%");
		int formattedNumber = numberFormat.parse("49,11").intValue();

		System.out.println("********** Parsing Locales formatted Numerics to primitives/Wrappers ********");
		System.out.println("Parsing BigDecimal from formatted price ($17.5) : " + formattedPrice);
		System.out.println("Parsing Double from percentage (41%): " + formattedTax);
		System.out.println("Parsing int from (49,113) : " + formattedNumber);
	}

	private static void resourceBundle() {
		// java.util.ResourceBundle
		ResourceBundle frBundle = ResourceBundle.getBundle(I18N_KEY, frLocale);
		String frenchTitle = frBundle.getString(APP_TITLE_KEY);
		String frenchHello = frBundle.getString(APP_HELLO_KEY);
		String defaultMessage = frBundle.getString("default");

		ResourceBundle usBundle = ResourceBundle.getBundle(I18N_KEY, usLocale);
		String usTitle = usBundle.getString(APP_TITLE_KEY);
		String usHello = usBundle.getString(APP_HELLO_KEY);

		System.out.println("********** ResouceBundle using Locales ********");
		System.out.println("using default bundle : " + defaultMessage);
		System.out.println("using i18n_fr.properties : " + frenchTitle + " " + frenchHello);
		System.out.println("using i18n_en_US.properties : " + usTitle + " " + usHello);

		// java.text.MessageFormat
		System.out.println("********** Pass values to bundle using MessageFormat ********");
		System.out.println(
				"passing values to i18n_fr.properties : " + MessageFormat.format(frenchHello, "Dupont", "42", "799"));
		System.out.println(
				"passing values to i18n_en_US.properties  : " + MessageFormat.format(usHello, "Doe", "21", "999"));

	}
}