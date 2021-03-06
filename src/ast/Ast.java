package ast;

import java.util.List;

import javax.swing.JDialog;
import org.antlr.v4.runtime.tree.TerminalNode;

import ast.components.Attributable;
import ast.constraints.Constraint;
import fields.JPanelContainer;
import generated.GuiInputParser.DialogContext;
import ui.Visitor;

public interface Ast<T> {
	Id getType();
	T accept(Visitor v);
	default String extractTitle(TerminalNode titleNode) {
		if(titleNode == null)
			throw new IllegalArgumentException("Invalid title name: " + titleNode);
		return checkNewLine(subString(titleNode.getText(), 1, 1));
	}
	
	default String checkNewLine(String text) {
		if(text != null && text.contains("\n") || text.contains("\r"))
			throw new IllegalArgumentException(text + " must not contain a new line");
		return text;
	}
	
	default String subString(String text, int offsetBegining, int offsetEnd) {
		return text.substring(offsetBegining, text.length()-offsetEnd);
	}

	record Query (Dialog dialog, List<Containable> containers) implements Ast<JDialog>{
		@Override
		public Id getType() {
			return Id.Query;
		}

		@Override
		public JDialog accept(Visitor v) {
			return v.visitQuery(this);
		}
	}
	interface Dialog extends Attributable<JDialog> {
		public String getTitle();
		public String getDescription();
		public List<Constraint> getConstraints();
		
		default List<Constraint> extractConstraints(DialogContext ctx) {
			return ctx.dialogCon() == null ? List.of() : extractConstraints(getType(), ctx.dialogCon().children);
		}
		
		default String extractDialogTitle(DialogContext ctx) {
			return extractTitle(ctx.QuotedCharText());
		}
		
		default String extractDialogDesc(DialogContext ctx) {
			return ctx.DefaultValue() == null? "": subString(ctx.DefaultValue().getText(), 2, 2);
		}
	}
	interface Containable extends Ast<JPanelContainer>{}
	interface Tabable extends Containable {}

}

