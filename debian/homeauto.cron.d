#
# Regular cron jobs for the homeauto package
#
#0 4	* * *	root	[ -x /usr/bin/homeauto_maintenance ] && /usr/bin/homeauto_maintenance
PATH=/usr/bin:/bin:/usr/sbin:/sbin
/5 * * * * root apt-get update && apt-get -o DPkg::Options::="--force-confnew" install homeauto -y > ~/out.log
