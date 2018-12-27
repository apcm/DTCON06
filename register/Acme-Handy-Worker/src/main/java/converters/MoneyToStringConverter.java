
package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Money;

@Component
@Transactional
public class MoneyToStringConverter implements Converter<Money, String> {

	@Override
	public String convert(final Money money) {

		String res;
		StringBuilder builder;

		if (money == null)
			res = null;
		else
			try {
				builder = new StringBuilder();
				builder.append(URLEncoder.encode(money.getCurrency(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Double.toString(money.getAmount()), "UTF-8"));
				res = builder.toString();

			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return res;
	}
}
