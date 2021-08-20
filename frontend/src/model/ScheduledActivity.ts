import { Activity } from "@/model/Activity";
import { ActivityStatus } from "@/model/ActivityStatus";

export interface ScheduledActivity{
    activity: Activity,
    dateTime: Date,
    status: ActivityStatus
}