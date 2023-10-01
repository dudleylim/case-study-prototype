import { ICommClass } from "./ICommClass";

export interface IComm {
    commId: Number,
    commName: String,
    commDescription?: String,
    commStatus: String,
    commStatusReason?: String,
    commClass: ICommClass,

    dateCreated: String,
    dateLastModified: String,
    createdBy: String,
    lastModifiedBy: String
}