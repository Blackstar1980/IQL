package ast.components;

import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import ast.Ast.Dialog;
import ast.constraints.Constraint;
import ast.Id;
import generated.GuiInputParser.DialogContext;
import ui.Visitor;

public class DMulti implements Dialog {
	private String title;
	private String description;
	private List<Constraint> constraints;
	public DMulti(DialogContext ctx) {
		title = extractDialogTitle(ctx);
		description = extractDialogDesc(ctx);
		constraints = extractConstraints(ctx);
	}
	
	public String getTitle() { return title; }

	public String getDescription() { return description; }

	public List<Constraint> getConstraints() {
		return Collections.unmodifiableList(constraints);
	}
	
	@Override
	public Id getType() { return Id.Multi; }

	@Override
	public String toString() {
		return "Multi[title=" + title + ", description=" + description + ", constraints=" + constraints + "]";
	}

	@Override
	public JDialog accept(Visitor v) {
		return v.visitMulti(this);
	}}
