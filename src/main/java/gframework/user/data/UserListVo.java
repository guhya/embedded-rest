package gframework.user.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserListVo {

	@XmlElementWrapper(name="userList")
	@XmlElement(name="user")
	private List<UserVo> userList;

	public UserListVo(){
	}
	
	public UserListVo(List<UserVo> userList){
		this.userList = userList;
	}
	
	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

	
}
