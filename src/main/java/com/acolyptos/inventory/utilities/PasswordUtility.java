package com.acolyptos.inventory.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtility {
  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public static boolean checkPassword(String plainPassword, String hashedPassword) {
    return BCrypt.checkpw(plainPassword, hashedPassword);
  }
}
