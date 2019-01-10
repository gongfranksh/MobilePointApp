package personal.wl.mobilepointapp.auth.ldap;

import android.os.AsyncTask;
import android.util.Log;

import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchRequest;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.SearchScope;

import static personal.wl.mobilepointapp.auth.ldap.LdapServer.LDAP_FILTER;

public class LdapSearchAsyn extends AsyncTask<String, Integer, User> implements LdapSearchListener {
    private String user_account;
    private String user_password;
    private User ldapuser;
    static final int TIME_LIMIT_SECONDS = 30;
    static final int SIZE_LIMIT = 100;
    private LdapSearchListener l = null;

    public LdapSearchAsyn(LdapSearchListener rst, String user_account, String user_password) {
        super();
        this.user_account = user_account;
        this.l = rst;
        this.user_password = user_password;
    }

    @Override
    protected User doInBackground(String... strings) {
        SearchResult result = null;
        LDAPConnection conn = null;
        try {
            conn = LdapServer.getConnection();
            final SearchRequest request = new SearchRequest(LdapServer.BASE_DN,
                    SearchScope.SUB, Filter.create(LDAP_FILTER));
            request.setSizeLimit(SIZE_LIMIT);
            request.setTimeLimitSeconds(TIME_LIMIT_SECONDS);
            result = conn.search(request);
            SearchResultEntry entry = queryDN(LdapServer.BASE_DN, LDAP_FILTER + user_account, conn);
            ldapuser = new User();
            if (entry == null) {
                return null;
            } else {
                conn.bind(entry.getDN(), user_password);
                ldapuser.setName(entry.getAttribute("name").getValue());
                ldapuser.setMailNickname(entry.getAttribute("mailNickname").getValue());
                ldapuser.setCompany(entry.getAttribute("company").getValue());
                ldapuser.setMobile(entry.getAttributeValue("mobile"));
                ldapuser.setMail(entry.getAttributeValue("mail"));
                ldapuser.setDepartment(entry.getAttributeValue("Department"));
                ldapuser.setDisplayName(entry.getAttributeValue("displayName"));
                ldapuser.setUsername(entry.getAttributeValue("displayName"));
                ldapuser.setsAMAccountName(entry.getAttributeValue("sAMAccountName"));
                ldapuser.setMailNickname(entry.getAttributeValue("mailNickname"));
                ldapuser.setTitle(entry.getAttributeValue("title"));
            }
        } catch (LDAPException lse) {
            Log.i("Ldap", "get result " + lse.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return ldapuser;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        l.onFinished(user);
    }

    public SearchResultEntry queryDN(String searchDN, String filter, LDAPConnection connection) {
        try {
            SearchRequest searchRequest = new SearchRequest(searchDN, SearchScope.SUB, "(" + filter + ")");
            SearchResult searchResult = connection.search(searchRequest);
            if (searchResult.getSearchEntries().size() > 0)
                return searchResult.getSearchEntries().get(0);
        } catch (LDAPSearchException e1) {
            e1.printStackTrace();
        } catch (LDAPException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public void onFinished(User ret) {


    }
}
