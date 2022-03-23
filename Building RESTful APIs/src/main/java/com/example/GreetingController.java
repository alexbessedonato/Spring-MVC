package com.example;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name,
			@RequestParam(value = "language", defaultValue = "english") String language) {
		String content;
		switch (language) {
		case "italian":
			content = String.format("Ciao, %s!", name.equals("World") ? "mondo" : name);
			break;
		case "german":
			content = String.format("Hallo, %s!", name.equals("World") ? "Welt" : name);
			break;
		case "french":
			content = String.format("Bonjour, %s!", name.equals("World") ? "monde" : name);
			break;
		case "spanish":
			content = String.format("Hola, %s!", name.equals("World") ? "mundo" : name);
			break;
		case "english":
			content = String.format("Hello, %s!", name);
			break;
		default:
			//content = "Unsupported Language";
			//break;
			throw new LanguageNotFoundException();
		}

		return new Greeting(counter.incrementAndGet(), content, language);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(LanguageNotFoundException.class)
	@ResponseBody ErrorInfo
	handleUnsupportedLanguage(HttpServletRequest req, Exception ex) {
	    return new ErrorInfo("Unsupported language");
	} 
	
}
