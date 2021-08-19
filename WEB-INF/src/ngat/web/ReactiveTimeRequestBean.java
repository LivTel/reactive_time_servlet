package ngat.web;

import java.util.regex.Matcher;

public class ReactiveTimeRequestBean {

	private final String NO_EMAIL = "NOEMAIL";
	private final String NOT_SELECTED = "NOT_SELECTED";
	private String tag, appName, appInst, appEmail, io_o, ringo3, moptop, frodospec, rise, lotus, sprat, io_i, totTime, proposalTitle, progId, sciCase, techCase, whyNow, humanWord;
	    
	public String getAppEmail() {
		return appEmail;
	}

	public String getIo_i() {
		return io_i;
	}

	public void setIo_i(String io_i) {
		this.io_i = io_i;
	}

	public String getIo_o() {
		return io_o;
	}

	public void setIo_o(String io_o) {
		this.io_o = io_o;
	}

	public String getRingo3() {
		return ringo3;
	}

	public void setRingo3(String ringo3) {
		this.ringo3 = ringo3;
	}

	public String getMoptop() {
		return moptop;
	}

	public void setMoptop(String moptop) {
		this.moptop = moptop;
	}

	public String getSprat() {
		return sprat;
	}

	public void setSprat(String sprat) {
		this.sprat = sprat;
	}
	
	public String getLotus() {
		return lotus;
	}

	public void setLotus(String lotus) {
		this.lotus = lotus;
	}

	public void setAppEmail(String appEmail) {
		this.appEmail = appEmail;
	}

	public String getAppInst() {
		return appInst;
	}

	public void setAppInst(String appInst) {
		this.appInst = appInst;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getFrodospec() {
		return frodospec;
	}

	public void setFrodospec(String frodospec) {
		this.frodospec = frodospec;
	}

	public String getRise() {
		return rise;
	}

	public void setRise(String rise) {
		this.rise = rise;
	}

	public String getProgId() {
		return progId;
	}

	public void setProgId(String progId) {
		this.progId = progId;
	}

	public String getProposalTitle() {
		return proposalTitle;
	}

	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}

	public String getSciCase() {
		return sciCase;
	}

	public void setSciCase(String sciCase) {
		this.sciCase = sciCase;
	}

	public String getTechCase() {
		return techCase;
	}

	public void setTechCase(String techCase) {
		this.techCase = techCase;
	}

	public String getTotTime() {
		return totTime;
	}

	public void setTotTime(String totTime) {
		this.totTime = totTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getWhyNow() {
		return whyNow;
	}

	public void setWhyNow(String whyNow) {
		this.whyNow = whyNow;
	}
	
	public String getHumanWord() {
		return humanWord;
	}

	public void setHumanWord(String humanWord) {
		this.humanWord = humanWord;
	}

	public boolean tagIsValid() {
		return !tag.equalsIgnoreCase(NOT_SELECTED);
	}
	
	//set to false if the email field begins with: noemail 
	public boolean shouldSendEmail() {
		
		String startText;
		try {
			startText = appName.substring(0, NO_EMAIL.length());
		} catch(IndexOutOfBoundsException e) {
			return true;
		}
		
		if (startText.equalsIgnoreCase(NO_EMAIL)) {
			return false;
		} else {
			return true;
		}
	}
	
	public String getInstrumentList() {
		String s = "";
		boolean tickedAnInstrument = false;
		if (getIo_o() != null) {
			tickedAnInstrument = true;
			s += "IO:O, ";
		}
		if (getRingo3() != null) {
			tickedAnInstrument = true;
			s += "RINGO3, ";
		}
		if (getMoptop() != null) {
			tickedAnInstrument = true;
			s += "MOPTOP, ";
		}
		if (getFrodospec() != null) {
			tickedAnInstrument = true;
			s += "FRODOSpec, ";
		}
		if (getRise() != null) {
			tickedAnInstrument = true;
			s += "RISE, ";
		}
		if (getSprat() != null) {
			tickedAnInstrument = true;
			s += "SPRAT, ";
		}
		if (getLotus() != null) {
			tickedAnInstrument = true;
			s += "LOTUS, ";
		}
		if (getIo_i() != null) {
			tickedAnInstrument = true;
			s += "IO:I, ";
		}
		
		if (tickedAnInstrument) {
			//remove ', '
			s = s.substring(0, s.length()-2);
		} else {
			s = "None selected";
		}
		return s;
	}
	
	public String formattedForEmail() {
		String s = "A Liverpool Telescope reactive time application form has been submitted on the LT website" + getLineBreak() + getLineBreak();
		s += "The details are as follows" + getLineBreak();
		s += "TAG: " + getTag() + getLineBreak();
		s += "Applicant Name: " + getAppName() + getLineBreak();
		s += "Applicant Institution: " + getAppInst() + getLineBreak();
		s += "Applicant Email: " + getAppEmail() + getLineBreak();
		s += "Instruments requested: " + getInstrumentList() + getLineBreak();
		s += "Total Time Requested: " + getTotTime() + getLineBreak();
		s += "Proposal Title: " + getProposalTitle() + getLineBreak();
		s += "Related LT Programme ID: " + getProgId() + getLineBreak();
		s += "Science Case: " + getSciCase() + getLineBreak();
		s += "Technical Case: " + getTechCase() + getLineBreak();
		s += "Why now, and not earlier?: " + getWhyNow() + getLineBreak();
		return s;
	}
	
	private String getLineBreak() {
		String s = "";
		char rtn = '\n';
		s += rtn;
		return s;
	}
	
	public void cleanXSSCharacters() {
		
		tag = cleanXSSCharactersInString(tag);
		appName = cleanXSSCharactersInString(appName);
		appInst = cleanXSSCharactersInString(appInst);
		appEmail = cleanXSSCharactersInString(appEmail);
		io_o = cleanXSSCharactersInString(io_o);
		ringo3 = cleanXSSCharactersInString(ringo3);
		moptop = cleanXSSCharactersInString(moptop);
		frodospec = cleanXSSCharactersInString(frodospec);
		sprat = cleanXSSCharactersInString(sprat);
		lotus = cleanXSSCharactersInString(lotus);
		io_i = cleanXSSCharactersInString(io_i);
		totTime = cleanXSSCharactersInString(totTime);
		proposalTitle = cleanXSSCharactersInString(proposalTitle);
		progId = cleanXSSCharactersInString(progId);
		sciCase = cleanXSSCharactersInString(sciCase);
		techCase = cleanXSSCharactersInString(techCase);
		whyNow = cleanXSSCharactersInString(whyNow);
		humanWord = cleanXSSCharactersInString(tag);
	}
	
	private String cleanXSSCharactersInString(String s) {

		if (s == null) {
			return null;
		}
		s = s.replaceAll("\"", "");
		s = s.replaceAll("<", "");
		s = s.replaceAll(">", "");
		return s;
	}
	
	public String toString() {
		String s = "";
		s += this.getClass().getName();
		s +="[";
		s +="tag=" +tag + ",";
		s +="appName=" +appName + ","; 
		s +="appInst=" +appInst + ",";
		s +="appEmail=" +appEmail + ",";
		s +="instruments=" +getInstrumentList() + ",";
		s +="totTime=" +totTime + ",";
		s +="proposalTitle=" +proposalTitle + ",";
		s +="progId=" +progId + ",";
		s +="sciCase=" +sciCase + ",";
		s +="techCase=" +techCase + ",";
		s +="whyNow=" +whyNow + ",";
		s +="]";
		return s;
		
	}
}
