<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<li>
		<h2 class='title'>Bố Cục Gợi Ý</h2>
		<div id='layoutsample'>
			<select class='listbox' size='2' id='content-select-tmp' onchange="listLayoutTmpOnChange()">
				<s:iterator begin="0" var="val" step="1" value="layout">
					<s:if test="type == 1">
						<option size-screen='<s:property value="size"/>' title='<s:property value="nameFull"/>' type='<s:property value="type"/>' direction='<s:property value="direction"/>' value="<s:property value="id"/>">
							<s:property value="nameFull"/> 
						</option>
					</s:if>
				</s:iterator>
				
			</select>
		</div>
	</li>
	<li>
		<h2>Bố Cục Nâng Cao</h2>
		<div id='layoutadvanced'>
			<select class='listbox' size='2' id='content-select' onchange="listLayoutOnChange()">
				<s:iterator begin="0" var="val" step="1" value="layout">
					<s:if test="type == 0">
						<option size-screen='<s:property value="size"/>' title='<s:property value="nameFull"/>' type='<s:property value="type"/>' direction='<s:property value="direction"/>' value="<s:property value="id"/>">
							<s:property value="nameFull"/> 
						</option>
					</s:if>
				</s:iterator>
			</select>
			
		</div>
	</li>
</ul>