<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.codify.min.js"></script>
<script type="text/javascript" src="js/htmlbox.colors.js"></script>
<script type="text/javascript" src="js/htmlbox.styles.js"></script>
<script type="text/javascript" src="js/htmlbox.syntax.js"></script>
<script type="text/javascript" src="js/htmlbox.undoredomanager.js"></script>
<script type="text/javascript" src="js/htmlbox.min.js"></script>
</head>
<h2>Blue Skin</h2>
<textarea id="htmlbox_icon_set_blue">This HtmlBox uses the default Icon Set</textarea>
<script language="Javascript" type="text/javascript">
	var hb_icon_set_blue = $("#htmlbox_icon_set_blue")
			.css("height", "400")
			.css("width", "600")
			.htmlbox(
					{
						toolbars : [
								[
								// Undo, Redo
								"separator", "undo", "redo",
								// Bold, Italic, Underline,
								"separator", "bold", "italic", "underline" ],
								[// Show code
								"separator", "code",
								// Formats, Font size, Font family, Font color, Font, Background
								"separator", "fontsize", "fontfamily",
										"separator", "fontcolor", "highlight", ] ],
						icons : "default",
						skin : "blue"
					});
</script>
