// Generated from GuiInput.g4 by ANTLR 4.4
package generated;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GuiInputParser}.
 */
public interface GuiInputListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#dialogCon}.
	 * @param ctx the parse tree
	 */
	void enterDialogCon(@NotNull GuiInputParser.DialogConContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#dialogCon}.
	 * @param ctx the parse tree
	 */
	void exitDialogCon(@NotNull GuiInputParser.DialogConContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#dialog}.
	 * @param ctx the parse tree
	 */
	void enterDialog(@NotNull GuiInputParser.DialogContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#dialog}.
	 * @param ctx the parse tree
	 */
	void exitDialog(@NotNull GuiInputParser.DialogContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#component}.
	 * @param ctx the parse tree
	 */
	void enterComponent(@NotNull GuiInputParser.ComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#component}.
	 * @param ctx the parse tree
	 */
	void exitComponent(@NotNull GuiInputParser.ComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#tab}.
	 * @param ctx the parse tree
	 */
	void enterTab(@NotNull GuiInputParser.TabContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#tab}.
	 * @param ctx the parse tree
	 */
	void exitTab(@NotNull GuiInputParser.TabContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#compCon}.
	 * @param ctx the parse tree
	 */
	void enterCompCon(@NotNull GuiInputParser.CompConContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#compCon}.
	 * @param ctx the parse tree
	 */
	void exitCompCon(@NotNull GuiInputParser.CompConContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull GuiInputParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull GuiInputParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#groupOrcomp}.
	 * @param ctx the parse tree
	 */
	void enterGroupOrcomp(@NotNull GuiInputParser.GroupOrcompContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#groupOrcomp}.
	 * @param ctx the parse tree
	 */
	void exitGroupOrcomp(@NotNull GuiInputParser.GroupOrcompContext ctx);
	/**
	 * Enter a parse tree produced by {@link GuiInputParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(@NotNull GuiInputParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link GuiInputParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(@NotNull GuiInputParser.GroupContext ctx);
}