package com.twovet.base.constant;

public interface Constants {
	public static class GENDER {
		public static String FEMALE = "Nữ";
		public static String MALE = "Nam";
		public static String UNDEFINE = "Không xác định";
	}
	public static class DATE_FORMAT {
		public static String DDMMYYYY = "dd/MM/yyyy";
	}
	
	public static class EXAMINA {
		public static String DRAG_DROP = "DRAGDROP";
		public static String CANCEL = "CANCEL";
		public static String ACCEPTED = "ACCEPTED";
	}
	
	public static class ROLE {
		public static String DOCTOR = "ROLE_DOCTOR";
		public static int ROLE_ID_DEFAULT = 3;
	}
	
	public static class EVENT {
		public static String ASSINED = "#28a745";
		public static String WAITING = "#ffc107";
		public static String ASSINED_TEXT = "#fff";
		public static String WAITING_TEXT = "#000";
		public static boolean IS_MODE_ADD = true;
		public static boolean IS_NOT_MODE_ADD = false;
	}
	
	public static class USER_DEFAULT {
		public static String PASSWORD_DEFAULT = "123456";
		public static int ENABLE_DEFAULT = 1;
		public static int DELETE_FLAG_DEFAULT = 0;
	}
	
	public static class EXAMINA_SCREEN {
		public static int INFO = 0;
		public static int CLINICAL = 1;
		public static int SUB_CLINICAL = 2;
		public static int CONCLUSION = 3;
	}
	
	public static class PROCESS_NUM {
		public static Long STEP_3 = 3L;
	}
	
}
