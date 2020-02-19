package com.kwt.app;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.kwt.app.service.PrintMessageService;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
	private PrintMessageService printMessageService;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		final TextField<String> name = new TextField<String>("username", Model.of(""));
		Form<Void> form = new Form<Void>("form") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				System.out.println("Onclick >" + name.getModelObject());
				printMessageService.addUser(name.getModelObject());
			}
		};

		form.add(new Label("message", printMessageService.printMessage()));
		form.add(name);
		add(form);
	}
}
