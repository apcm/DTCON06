
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Money;

@Component
@Transactional
public class StringToMoneyConverter implements Converter<String, Money> {

	@Override
	public Money convert(final String text) {

		Money res;
		final String parts[];

		if (text == null)
			res = null;
		else
			try {
				parts = text.split("\\|");
				res = new Money();
				res.setAmount(Double.valueOf(URLDecoder.decode(parts[0], "UTF-8")));
				res.setCurrency(URLDecoder.decode(parts[1], "UTF-8"));
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return res;
	}
}
