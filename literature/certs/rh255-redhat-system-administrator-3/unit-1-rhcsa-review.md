Unit 1: RCSHA review
=================

## RPM
     * tarball with metadata (where files go, permissions, SELinux etc)

## ISET rule

            * Install
            * Start
            * Enable
            * Test

## Virtualization Review

```
# virsh list
 Id    Name                           State
----------------------------------------------------
 1     vserver                        running

# virsh list --all
 Id    Name                           State
----------------------------------------------------
 1     vserver                        running

# virsh start vserver
# virsh destroy vserver
# virsh console vserver
```
?? What is the command to get console to VM?

## Single-user mode

* Bypass the root password
* How to get into single-user mode
    ```
    > init 1
    ```
  * Append argument to kernel command at boot (```s``` or ```1``` or ```single```)

## Users

    * local
        * ```/etc/passwd```: <uname>:<passwd>:<uid>:<gid>:<comment>:<home_dir>:<shell>
        * ```/etc/shadow```
        * hash: $<hash_type>$<salt>$<hash>
    * network
        * centralised management (LDAP etc)

## Package Group install

    * yum groupinstall "directory-client"
     --? How do you install package groups?

## LDAP

    * Need 3 things
        * LDAP Server's FQDN
            * Base DN - top of the tree. Used to search for LDAP entries (user, group info)
            --? What is Base DN?
           * CA Cert
        * Config LDAP with system-config-authentication
        ?? Which GUI tool can be used to config LDAP?

        * Use ```getent``` to query LDAP

        ```
        # getent passwd ldapuser1
        ldapuser1:x:1701:1701:LDAP Test User 1:/home/guests/ldapuser1:/bin/bash
        ```
        ?? Which command-line tool is used to query LDAP?

        * autofs is used to automount shares for LDAP users?
            * auto.master -> master config file for ```autofs```
                * 3 things needed:
                   * server name
                   * exported share
                   * local mount point
                   * mount options
                * ```showmount``` is used to query NFS servers for shares

                    ```
                    # showmount -e instructor.example.com
                Export list for instructor.example.com:
                /home/guests 192.168.0.0/255.255.255.0
                /var/nfs     192.168.1.0/255.255.255.0,192.168.0.0/255.255.255.0
                /kickstart   192.168.1.0/255.255.255.0,192.168.0.0/255.255.255.0
                /var/ftp/pub 192.168.1.0/255.255.255.0,192.168.0.0/255.255.255.0
                    ```

                 * ```service autofs reload``` after updating auto.guests

## SSH

      * ```ssh -X user@servername```
      * Public key of remote machine stored locally under ```~/.ssh/known_hosts```
      * ```/etc/ssh/sshd_config``` examples
          * Set ```PermitRootLogin = no``` to deny root login
          * Set ```PasswordAuthentication = no``` to deny password authentication
