# Introduction #

The introduction of OTA Hotel Messages


# Details #


  * OTA\_HotelAvail
    1. Query Rates

  * OTA\_HotelAvailGet
    1. Query Availability Status
    1. Query Booking Limits (Related with Inventory?)

  * OTA\_HotelAvailNotif
    1. Similar with OTA\_HotelAvailGet, but with reversed direction : provider -> channel

  * OTA\_HotelInvCountNotif
    1. The Inventory Count Notification is used to send base inventory levels by inventory code, (e.g., room type code) to establish the physical inventory count. An Inventory Notification should always precede an Inventory Count Notification to establish the existence of inventory codes in the reservation system.
    1. The physical inventory is the basis by which availability is determined. However, additional calculations figure into assigning the inventory counts for availability. Availability is a commitment to sell a room at a specific rate or plan. Since the same rooms may be sold under different rate plans, a system may carry a discrete inventory, or an inventory count in association with different rates. The superset of inventory may be greater than the physical count, with the actual number of rooms counted down when they are sold.

  * OTA\_HotelInvNotif
    1. The Hotel Inventory Notification message is the message that sends the notification of the creation of a new inventory item, such as a room type or service type that did not previously exist at a hotel property.
    1. The Hotel Inventory Notification message should precede the Inventory Count Notification and Rate Plan Notification messages. The Inventory Count Notification establishes the physical inventory count by inventory code, and a Rate Plan Notification assigns a rate plan to the inventory item.

  * OTA\_HotelRateAmountNotif
    1. The Hotel Rate Amount Notification message notifies a booking source of changes in the rates charged for room and non-room products of a hotel.
    1. The creation of a new rate plan is done through the Rate Plan Notification message.

  * OTA\_HotelRatePlan
    1. The HotelRatePlan message provides the ability to search for active rate plans and offers. The message will be used primarily to ensure that two systems are in sync. It can used in place of the HotelRatePlanNotif message when a pull model is desired.

  * OTA\_HotelRatePlanNotif
    1. The Hotel Rate Plan Notification message is used to notify a booking source of a new rate plan created for a hotel, or to modify and synchronize existing rate plans between systems.