package personal.wl.mobilepointapp.auth.ldap;

import android.util.Log;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;

public class LdapServer {
    // The port number of the directory server.


//    ldap 绑定信息
//    server 192.168.72.31
//    user  ldap@buynow.com.cn
//    base  ou=buynow,dc=buynow,dc=com,dc=cn
//    pass ``11qqww
//    filter    sAMAccountName=%s

    private static final int PORT = 389;

    // The base DN to use when searching the server.
    public static final String BASE_DN = "ou=buynow,dc=buynow,dc=com,dc=cn";

    // The address of the directory server.
    private static final String HOST = "192.168.72.31";

    private static final String BIND_DN = "ldap@buynow.com.cn";
    private static final String BIND_PW = "``11qqww";
//    public static final String LDAP_FILTER = "sAMAccountName=";
    public static final String LDAP_FILTER = "mail=";



    private static LDAPConnection conn;

    public static LDAPConnection getConnection() {
        if (conn == null) {
            try {
                conn = new LDAPConnection(HOST, PORT, BIND_DN, BIND_PW);
            } catch (LDAPException e) {
                Log.i("LDAPSERVER", "getConnection: "+e.toString());
            }
        }

        return conn;
    }


}
