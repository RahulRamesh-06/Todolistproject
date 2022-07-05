import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MasterService } from 'src/app/services/master.service';
import { SecurityService } from 'src/app/services/security/security.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  
  itemName: string;
  itemDescription: string;
  listItems: Array<any> = []

  addedItems:Array<any> = []
  
  constructor(
    private masterService: MasterService, 
    private securityService: SecurityService, 
    private toastrService: ToastrService
  ) { }
  
  ngOnInit() {
    // Fetch saved ToDo items against loggedin user
    this.addedItems=[];
    this.masterService.getToDoList().subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
    });
  }
  
  /**
   * Check or uncheck ToDO items
   * @param itemIndex 
   */
  toggleStatus(itemIndex: number){
    this.listItems[itemIndex].completed = !this.listItems[itemIndex].completed
  }

  /**
   * Append item to ToDo list
   * @param itemName 
   * @param itemDescription 
   */
  addItem(itemName: string, itemDescription: string){
    this.addedItems.push({
      userId: this.securityService.details.userID,
      task:itemName,
      description: itemDescription,
      isChecked: false,
      lastUpdatedBy:'',
      lastUpdatedTime:''
    });
    this.itemName = null;
    this.itemDescription = null;
    this.toastrService.success('Task added successfully');
    this.masterService.saveToDoList(this.addedItems).subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
      this.ngOnInit();
    }, err => this.toastrService.error(err.message ? err.message : err));
  }

  /**
   * Remove ToDo item from the list
   * @param itemIndex 
   */
  // removeItem(itemIndex: any){
  //   // this.listItems.splice(itemIndex, 1);
  //   // const item = this.listItems.findIndex(itemIndex);
  //   console.log('itemid '+itemIndex)
  //   this.masterService.deleteFromList(itemIndex.taskId).subscribe((res) => {
  //     this.toastrService.success('List fetched successfully');
  //     this.ngOnInit();
  //   }, err => this.toastrService.error(err.message ? err.message : err));
    
  //   this.toastrService.warning('Task removed successfully');
  // }
  removeItem(itemIndex: any){
    // const item = this.listItems.findIndex(itemIndex);
    this.listItems.splice(itemIndex, 1);
    this.toastrService.warning('Task removed successfully');
  }

  /**
   * Save updated ToDo items list against logged-in user
   */
  saveList() {
    
    this.masterService.saveToDoList(this.addedItems).subscribe((res) => {
      this.listItems = res;
      this.toastrService.success('List fetched successfully');
      this.ngOnInit();
    }, err => this.toastrService.error(err.message ? err.message : err));
  }

  /**
   * Log out from server
   */
  logout() {
    this.securityService.logout();
  }

  
}
