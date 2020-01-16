<html>
<head>
<title>LT Reactive Time Application Form</title>

</head>
<body>

<b>LT Reactive Time Application Form</b> <br><br>

<form method="post" action="request_servlet">
<p>
We have kept this web-page as simple as possible in order to ensure maximum compatability across browsers. However if you have problems with the form, please contact us (<a href="http://telescope.livjm.ac.uk/Contact/">using this page</a>) with details of the browser and platform you were using.
</p>
<p>
Please be aware that some characters will be removed from the text. These include the &gt;, and &lt; symbols and the " character. This is for simple protection against XSS scripting attacks.
</p>
<p>
Not all TAGs set aside time for use by the REACTIVE programme. If you believe your approriate TAG is accepting REACTIVE applications but is not listed on the form, please <a href='http://telescope.livjm.ac.uk/Contact/'>contact us</a>.
</p>
<table width="1043">
<tbody>
	<tr>
		<td align="center" width="115">TAG: </td>
		<td align="left" width="115">
			<br>
			PATT: For PIs based in the UK
			<br>
			<!-- CAT: For PIs based in Spain
			<br> -->
			JMU: For Liverpool John Moores University internal PIs
			<br><br>
			Please select: 
			<select name="tag">
				<option value="NOT_SELECTED" default="true">Please select</option>
				<option value="PATT">PATT</option>
				<!-- <option value="CAT">CAT</option> -->
				<option value="JMU">JMU</option>
			</select>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td align="right" width="115">Applicant Name: </td>
		<td align="left" width="928">
			<input size="40" text="" name="appName" type="">
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Applicant Institution: </td>
		<td align="left" width="928">
			<input size="40" text="" name="appInst" type="">
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Applicant Email: </td>
		<td align="left" width="928">
			<input size="40" text="" name="appEmail" type="">
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Instruments requested: </td>
		<td align="left" width="464"></td>
	</tr>
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="io_o">IO:O<BR>
		</td>
	</tr>
	<!--
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="ringo3">RINGO3<BR>
		</td>
	</tr>
	-->
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="frodospec">FRODOSpec<BR>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="rise">RISE<BR>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="sprat">SPRAT<BR>
		</td>
	</tr>
	<!-- <tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="lotus">LOTUS<BR>
		</td>
	</tr> -->
	<tr>
		<td></td>
		<td>
			<INPUT TYPE=CHECKBOX NAME="io_i">IO:I<BR>
		</td>
	</tr>
	<tr>
		<td align="right" width="115">Total Time Requested: </td>
		<td align="left" width="464">
			<input text="" name="totTime" type="">
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Proposal Title: </td>
		<td align="left" width="928">
			<input size="60" text="" name="proposalTitle" type="">
	 	</td>
	</tr>
	<tr>
		<td colspan="2">
			If your request relates to an existent LT programme, please give the programme ID:
		</td>
	</tr>
	<tr>
		<td align="right" width="115">Prog. ID: </td>
		<td align="left" width="928">
			<input  size="60" text="" name="progId" type="">
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Science Case: (max. 3000 char)</td>
		<td align="left" width="928">
			<textarea name = "sciCase" rows="16" cols="60" placeholder="Be brief and clear. Concentrate more on describing the observations you wish to make, how they fit into your research and their implications to the field, rather than extensive theoretical background. Maximum 3000 characters. Typically 300-400 words."></textarea>
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Technical Case: (max. 3000 char)</td>
		<td align="left" width="928">
			<textarea name = "techCase" rows="16" cols="60" placeholder="Maximum 3000 characters. Typically fewer than 200 words."></textarea>
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Why this proposal was not submitted to the last open call: (max. 3000 char)</td>
		<td align="left" width="928">
			<textarea name = "whyNow" rows="16" cols="60"></textarea>
	 	</td>
	</tr>
	<tr>
		<td align="right" width="115">Please type the word "reactive" (without the quotation marks) into the following text box.</td>
		<td align="left" width="928">
			<input size="40" text="" name="humanWord" type="">
	 	</td>
</tbody>
</table>
<p>
Everything submitted on this form will be automatically forwarded by email to all members of the Time Allocation Committee to whom you are applying. See the LT <a href="http://telescope.livjm.ac.uk/Privacy/">Privacy Statement</a>.
</p>
<input value="Submit Request" type="submit"> <br>
<br>

</form>
</body>
</html>
