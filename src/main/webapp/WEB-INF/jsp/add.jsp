<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Instruction Queue</title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>

<body>
	<h2>Instruction Queue - Add an Instruction Messages to the Instruction Queue</h2>

       <ul>
        <li><a href="/instruction-queue">Home</a></li>
        <li><a href="/instruction-queue/size">Number of Instruction Messages in Instruction Queue</a></li>
        <li><a href="/instruction-queue/add">Add an Instruction Messages to the Instruction Queue</a></li>
        <li><a href="/instruction-queue/clear">Clear all Instruction Messages from the Instruction Queue</a></li>
        <li><a href="/instruction-queue/poll">Get Instruction Messages from the front of Instruction Queue</a></li>
       </ul>

	<form:form method="POST" commandName="message" action="add">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td>Instruction Type :</td>
				<td><form:input path="instructionType" /></td>
				<td><form:errors path="instructionType" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Product Code :</td>
				<td><form:input path="productCode" /></td>
				<td><form:errors path="productCode" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Quantity :</td>
				<td><form:input path="quantity" /></td>
				<td><form:errors path="quantity" cssClass="error" /></td>
			</tr>
			<tr>
				<td>UOM :</td>
				<td><form:input path="uom" /></td>
				<td><form:errors path="uom" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Timestamp :</td>
				<td><form:input path="timestamp" /></td>
				<td><form:errors path="timestamp" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input value="Add" type="submit" /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>