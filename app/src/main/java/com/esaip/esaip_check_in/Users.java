package com.esaip.esaip_check_in;

public class Users {
        private String IDGoogle;
        private Long TimeStamp;
        private String Room;
        private Users(){
        }
        public Users(String IDGoogle, Long TimeStamp, String Room) {
            this.IDGoogle = IDGoogle;
            this.TimeStamp = TimeStamp;
            this.Room = Room;
        }

}
