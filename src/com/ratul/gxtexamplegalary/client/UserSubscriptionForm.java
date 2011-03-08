package com.ratul.gxtexamplegalary.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class UserSubscriptionForm extends LayoutContainer {

    private FormData formData;
    private Window window;
    private TextField<String> tfEmail;
    private TextField<String> tfName;
    private Status statusMessage;
    private Button btnSave = new Button("Subscribe Comments");
    public UserSubscriptionForm()
    {
        initComponent();
    }
    public void setWindow(Window w)
    {
    	window = w;
    }
    private void initComponent()
    {
        setLayout(new FlowLayout(10));

        formData = new FormData("-20");

        FormPanel subscriptionForm = new FormPanel();
        subscriptionForm.setHeaderVisible(false);
        subscriptionForm.setFrame(true);
        subscriptionForm.setWidth(400);

        tfName = new TextField<String>();
        tfName.setFieldLabel("Name");
        tfName.setAllowBlank(false);
		subscriptionForm.add(tfName, formData);

		tfEmail = new TextField<String>();
		tfEmail.setFieldLabel("Email");
		tfEmail.setValidator(new Validator() {
			
			@Override
			public String validate(Field<?> field, String value) {
				if (field == tfEmail) {
	                if (!tfEmail.getValue().toLowerCase().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
	                    return "E-mail Address is not valid";
	                }
	            }

	            return null;
			}
		});
		tfEmail.setAllowBlank(false);
		subscriptionForm.add(tfEmail, formData);

		statusMessage = new Status();
        statusMessage.setText("");
        statusMessage.setWidth(50);
        
        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                saveTask();
            }
        });
        
        subscriptionForm.addButton(btnSave);
        Button btnCancel = new Button("Cancel");
        btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });

        subscriptionForm.addButton(btnCancel);
        subscriptionForm.getButtonBar().add(statusMessage);

        subscriptionForm.setButtonAlign(HorizontalAlignment.CENTER);

        FormButtonBinding binding = new FormButtonBinding(subscriptionForm);
        binding.addButton(btnSave);

        add(subscriptionForm);
    }

    private void saveTask()
    {
        statusMessage.setBusy("");
        if (!tfName.getRawValue().equals("") && !tfEmail.getRawValue().equals("")) {
			GreetingService.Util.getInstance().saveSubscribedUser(tfName.getRawValue(),tfEmail.getRawValue(),
					new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Info.display("Failure:(","Subscription Failed.");
							statusMessage.clearStatus("");
						}

						@Override
						public void onSuccess(Void result) {
							Info.display("Congratulation","You are subscribed.");
							statusMessage.clearStatus("");
							tfName.setValue("");
							tfEmail.setValue("");
							window.hide();
						}
					});

		}
    }

    
}
