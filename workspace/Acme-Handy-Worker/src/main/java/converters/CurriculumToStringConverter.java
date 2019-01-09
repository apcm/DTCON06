
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Curriculum;

@Component
@Transactional
public class CurriculumToStringConverter implements Converter<Curriculum, String> {

	@Override
	public String convert(final Curriculum curriculum) {
		String res;

		if (curriculum == null)
			res = null;
		else
			res = String.valueOf(curriculum);

		return res;
	}

}
