package com.example.winyourlife.data.dto

import com.example.winyourlife.presentation.dataObjects.NotificationData
import java.util.UUID

data class NotificationResponse(val id:UUID, val emailSender:String, val type:String, val time:String, val nickSender:String)
{

}
