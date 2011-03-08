package com.ratul.gxtexamplegalary.client;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CommentForm extends LayoutContainer {

    private FormData formData;
    private Window window;
    private TextArea taComment;
    private TextField<String> tfName;
    private Status statusMessage;
    private Button btnSave = new Button("Post Comment");
    
    public CommentForm()
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

        FormPanel commentForm = new FormPanel();
        commentForm.setHeaderVisible(false);
        commentForm.setFrame(true);
        commentForm.setWidth(500);

        tfName = new TextField<String>();
        tfName.setFieldLabel("Name");
        tfName.setAllowBlank(false);
        commentForm.add(tfName, formData);

        taComment = new TextArea();
        taComment.setPreventScrollbars(true);
        taComment.setFieldLabel("Comment");
        taComment.setAllowBlank(false);
        commentForm.add(taComment, formData);

        statusMessage = new Status();
        statusMessage.setText("");
        statusMessage.setWidth(50);
        
        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                saveTask();
            }
        });
        
        commentForm.addButton(btnSave);
        Button btnCancel = new Button("Cancel");
        btnCancel.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });

        Button subscribeButton = new Button("Subscribe");
		subscribeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				UserSubscriptionForm userSubsForm = new UserSubscriptionForm();
				Window subscribeUserWn = getWindow("Subscribe to be updated with comments",180,430,userSubsForm);
				userSubsForm.setWindow(subscribeUserWn);
				subscribeUserWn.show();
			}
		});
		
		commentForm.addButton(subscribeButton);
        commentForm.addButton(btnCancel);
        commentForm.getButtonBar().add(statusMessage);

        commentForm.setButtonAlign(HorizontalAlignment.CENTER);

        FormButtonBinding binding = new FormButtonBinding(commentForm);
        binding.addButton(btnSave);

        add(commentForm);
    }

    private void saveTask()
    {
        statusMessage.setBusy("Comment is posting...");
        if (!tfName.getRawValue().equals("") && !taComment.getRawValue().equals("")) {
			GreetingService.Util.getInstance().postComment(taComment.getRawValue(),tfName.getRawValue(),
					new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Info.display("Failure:(","Comment is not posted.");
							statusMessage.clearStatus("");
						}

						@Override
						public void onSuccess(Void result) {
							Info.display("Success:)","Comment is posted successfully.");
							statusMessage.clearStatus("");
							tfName.setValue("");
							taComment.setValue("");
							window.hide();
						}
					});

		}
    }
    private Window getWindow(String header, int height, int width, LayoutContainer container) {
		Window win = new Window();
		win.setMaximizable(true);
		win.setHeading(header);
		win.setWidth(width);
		win.setHeight(height);
		win.add(container);
		return win;
	}

    
}
