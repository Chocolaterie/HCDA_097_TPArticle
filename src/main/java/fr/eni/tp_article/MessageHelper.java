package fr.eni.tp_article;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

@Component
public class MessageHelper {

	@Autowired
	MessageSource messageSource;

	@Autowired
	LocaleResolver localeResolver;

	public Locale getLocale() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null && attributes.getRequest() != null) {
			return localeResolver.resolveLocale(attributes.getRequest());
		}

		return Locale.getDefault();
	}

	public String i18n(String message) {

		return messageSource.getMessage(message, null, getLocale());
	}

	public String i18n(String message, @Nullable Object... args) {

		return messageSource.getMessage(message, args, getLocale());
	}
}
