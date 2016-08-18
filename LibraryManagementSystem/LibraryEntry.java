

public class LibraryEntry {
	Integer id;
	String type;
	String name;
	String additionalinfo;
	boolean available;
	boolean overdue;
	Integer noofcopies;
	Integer sid;
	String nameofstudent;
	String cin;
	String dateborrowed;
	String duedate;
	String datereturned;
	Integer tid;
	String newtype;
	
	public LibraryEntry(Integer tid,String newtype){
		this.tid=tid;
		this.newtype=newtype;
	}
	
	
	public LibraryEntry(Integer id,String type,String name,String additionalinfo,boolean available,boolean overdue,Integer noofcopies){
		this.id=id;
		this.type=type;
		this.name=name;
		this.additionalinfo=additionalinfo;
		this.available=available;
		this.overdue=overdue;
		this.noofcopies=noofcopies;
	}
	
	public boolean isOverdue() {
		return overdue;
	}


	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}


	public LibraryEntry(Integer sid,Integer id,String cin,String nameofstudent,String dateborrowed,String duedate,String datereturned){
		this.id=id;
		this.sid=sid;
		this.cin=cin;
		this.nameofstudent=nameofstudent;
		this.dateborrowed=dateborrowed;
		this.duedate=duedate;
		this.datereturned=datereturned;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdditionalinfo() {
		return additionalinfo;
	}

	public void setAdditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Integer getNoofcopies() {
		return noofcopies;
	}

	public void setNoofcopies(Integer noofcopies) {
		this.noofcopies = noofcopies;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getNameofstudent() {
		return nameofstudent;
	}

	public void setNameofstudent(String nameofstudent) {
		this.nameofstudent = nameofstudent;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getDateborrowed() {
		return dateborrowed;
	}

	public void setDateborrowed(String dateborrowed) {
		this.dateborrowed = dateborrowed;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getDatereturned() {
		return datereturned;
	}

	public void setDatereturned(String datereturned) {
		this.datereturned = datereturned;
	}

}
	

