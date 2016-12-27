package lxf.androiddemos.model;

public class UserEntity {
	private String name;
	private String sex;
	private int age;
	private int type;

	public String initType(int type){
		String result;
		switch (type){
			case 1:
				result = "程序猿";
				break;
			case 2:
				result = "程序猿的天敌";
				break;
			default:
				result = "无业游民";
				break;
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}