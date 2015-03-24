Overview

The system enables personnel to book resources, such as rooms or equipments, for their own use at specified times.
Analysis

The system must support of the following use cases:

  * 
> > User to login. Authenticates is done via htaccess
  * 
> > User to view the current bookings of a selected resource
  * 
> > User to book a reource, if available, at a specified time. Bookings can be done on the hour or on the half-hour for a duration that is a multiple of 30 min. You can assume that bookings do not span the midnight boundary.
  * 
> > User to make an iterated booking of a selected resource. In such a booking, the user specifies the start and end dates, the start time, the duration, and the frequency. The frequency can be a given set of dates or selected from predefined classes, such as “Second Friday of Each Month” and “Every Monday”.
  * 
> > The “admin” user to add, edit, or delete a resource. Each resource has an ID, a location, and a description.
  * 
> > The “admin” user to delete bookings made by others. In that case, an email is auto-sent to them with a reason for the deletion.