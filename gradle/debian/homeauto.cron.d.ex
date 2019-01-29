#
# Regular cron jobs for the homeauto package
#
0 4	* * *	root	[ -x /usr/bin/homeauto_maintenance ] && /usr/bin/homeauto_maintenance
