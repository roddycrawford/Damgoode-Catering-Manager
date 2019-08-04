/**
 * 
 */

var dgCateringApp = angular.module('dgCateringApp', ['ngRoute']);

dgCateringApp.config(function($routeProvider) {
	$routeProvider
//	.when('/home', {
//		templateUrl : 'home.html'
//	})
	.when('/orders', {
		templateUrl : 'orders.html',
		controller : 'orderScreenController'
	})
	.when('/addresses', {
		templateUrl : 'addresses.html',
		controller : 'addressScreenController'
	})
	.when('/customers', {
		templateUrl : 'customers.html',
		controller : 'customerScreenController'
	})
	.when('/dailynotes', {
		templateUrl : 'dailynotes.html',
		controller : 'dailyNotesScreenController'
	})
	.when('/stacks', {
		templateUrl : 'stacks.html',
		controller : 'stackScreenController'
	})
	.when('/resume', {
		templateUrl : 'resume.html',
		controller : 'resumeScreenController'
	})
	.otherwise({
		redirectTo : '/orders'
	});
});

dgCateringApp.filter('phoneNumber', function() {
    return function(phone) {
        var result = "(";
        for (var i = 0; i <= 2; i++) 
        {
            result += phone[i];
        }
        result += ") ";
        for (var i = 3; i <= 5; i++)
        {
        	result += phone[i];
        }
        result += "-";
        for (var i = 6; i < phone.length; i++)
        {
        	result += phone[i];
        }   
        return result;
    };
});

dgCateringApp.controller('masterController', function($scope, $http) {
	$scope.useDemoDatabase = true;
	$scope.customerSearch = '';
	$scope.todaysDate = new Date();
	
	$scope.homeScreenActive = '';
	$scope.customerScreenActive = '';
	$scope.dailyNoteScreenActive = '';
	$scope.stackScreenActive = '';
	$scope.resumeScreenActive= '';
	
	$scope.updateOrderNavbar = function() {
		$scope.homeScreenActive = 'active';
		$scope.customerScreenActive = '';
		$scope.dailyNoteScreenActive = '';
		$scope.stackScreenActive = '';
		$scope.resumeScreenActive= '';
	};
	
	$scope.updateCustNavbar = function() {
		$scope.homeScreenActive = '';
		$scope.customerScreenActive = 'active';
		$scope.dailyNoteScreenActive = '';
		$scope.stackScreenActive = '';
		$scope.resumeScreenActive= '';
	};
	
	$scope.updateDailyNoteNavbar = function() {
		$scope.homeScreenActive = '';
		$scope.customerScreenActive = '';
		$scope.dailyNoteScreenActive = 'active';
		$scope.stackScreenActive = '';
		$scope.resumeScreenActive= '';
	};
	
	$scope.updateStackNavbar = function() {
		$scope.homeScreenActive = '';
		$scope.customerScreenActive = '';
		$scope.dailyNoteScreenActive = '';
		$scope.stackScreenActive = 'active';
		$scope.resumeScreenActive= '';
	};
	
	$scope.updateResumeNavbar = function() {
		$scope.homeScreenActive = '';
		$scope.customerScreenActive = '';
		$scope.dailyNoteScreenActive = '';
		$scope.stackScreenActive = '';
		$scope.resumeScreenActive= 'active';
	};
	
	$scope.emptyCustomer = 
		{
	        address: {
	        	addressId: 0,
		        addressNote: '',
		        addressType: '',
		        addressTypeAbr: '',
		        businessName: '',
		        city: '',
		        roomNum: 0,
		        state: '',
		        streetName: '',
		        streetNum: '',
		        zipCode: ''
	        },
	        customerFirstName: '',
	        customerId: 0,
	        customerLastName: '',
	        customerNote: '',
	        email: '',
	        phoneNumber: "0000000000"
		};
	
	$scope.emptyAddress = 
		{
		    addressId: 0,
		    addressNote: '',
		    addressType: '',
		    addressTypeAbr: '',
		    businessName: '',
		    city: '',
		    roomNum: 0,
		    state: '',
		    streetName: '',
		    streetNum: '',
		    zipCode: ''
		};
	
	$scope.emptyOrder =	
		{
			cateringFee:0,
			customer: {
				address: {
		        	addressId: 0,
			        addressNote: '',
			        addressType: '',
			        addressTypeAbr: '',
			        businessName: '',
			        city: '',
			        roomNum: 0,
			        state: '',
			        streetName: '',
			        streetNum: '',
			        zipCode: ''
		        },
		        customerFirstName: '',
		        customerId: 0,
		        customerLastName: '',
		        customerNote: '',
		        email: '',
		        phoneNumber: "0000000000"
			},
			deliveryFee: 4,
			dollarDiscountAmt: 0,
			hasDealsApplied: false,
			hasDeliveryFee: true,
			hasDollarDiscount: false,
			hasPercentageDiscount: false,
			isBilledOrder: false,
			isBuffetOrder: false,
			isInPos: false,
			isPaid: false,
			isTaxExempt: false,
			isVoid: false,
			items: [],
			numPeopleToFeed: 0,
			orderId: 0,
			orderNote: '',
			paymentType: '',
			percentageDiscountAmt: 0,
			percentageDiscountRate: 0,
			pricePerHead: 0,
			subtotal: 0,
			taxAmount: 0,
			total: 0,
			voidReason:	''	
		};
	
	$scope.companySettings = 
	{
	    "standardCateringRate": 0.1,
	    "standardDeliveryFee": 4,
	    "taxRate": 0.11
	};
	
//	$scope.getCompanySettings = function() {
//		console.log('getCompanySettings()');
//		
//		console.log('companySettings from database');
//		
//		$http.get("/dgcateringapi/v1/catering/companysettings")
//		.then(function(response) {
//			console.log('response data: ' + angular.toJson(response.data));
//			$scope.companySettings = angular.toJson(response.data);
//			console.log('Number of Company Settings: ' + $scope.companySettings.length);
//		}, function(response) {
//			console.log('error HTTP GET Company Settings: ' + response.status);
//		});
//	};
	
//	$scope.customers =
//		[
//		    {
//		        "address": {
//		            "addressId": 1,
//		            "addressNote": "Number 2 has taken over number 1",
//		            "addressType": "Business",
//		            "addressTypeAbr": "Suite",
//		            "businessName": "Damgoode Pies #2",
//		            "city": "Little Rock",
//		            "roomNum": 100,
//		            "state": "AR",
//		            "streetName": "Cantrell Rd.",
//		            "streetNum": "6706",
//		            "zipCode": "72207"
//		        },
//		        "addressId": 1,
//		        "customerFirstName": "Roddy",
//		        "customerId": 1,
//		        "customerLastName": "Crawford",
//		        "customerNote": "Man what a super great guy",
//		        "email": "roddy@damgoode.com",
//		        "phoneNumber": "5013520642"
//		    },
//		    {
//		        "address": {
//		            "addressId": 2,
//		            "addressNote": "oh durango",
//		            "addressType": "Apartment",
//		            "addressTypeAbr": "Apt",
//		            "businessName": "Mtn Apts",
//		            "city": "Durango",
//		            "roomNum": 100,
//		            "state": "CO",
//		            "streetName": "Forever Rd.",
//		            "streetNum": "1000",
//		            "zipCode": "46821"
//		        },
//		        "addressId": 2,
//		        "customerFirstName": "Laura",
//		        "customerId": 2,
//		        "customerLastName": "Crawford",
//		        "customerNote": "What a pretty lady",
//		        "email": "lkhope89@gmail.com",
//		        "phoneNumber": "5016281709"
//		    },
//		    {
//		        "address": {
//		            "addressId": 4,
//		            "addressNote": "added address",
//		            "addressType": "School",
//		            "addressTypeAbr": "Room",
//		            "businessName": "Arkansas Coding Academy",
//		            "city": "Conway",
//		            "roomNum": 100,
//		            "state": "AR",
//		            "streetName": "Oak St.",
//		            "streetNum": "65157",
//		            "zipCode": "72734"
//		        },
//		        "addressId": 4,
//		        "customerFirstName": "Hadley",
//		        "customerId": 3,
//		        "customerLastName": "Crawford",
//		        "email": "tinyprincess@gmail.com",
//		        "phoneNumber": "5019876541"
//		    },
//		    {
//		        "address": {
//		            "addressId": 4,
//		            "addressNote": "added address",
//		            "addressType": "School",
//		            "addressTypeAbr": "Room",
//		            "businessName": "Arkansas Coding Academy",
//		            "city": "Conway",
//		            "roomNum": 100,
//		            "state": "AR",
//		            "streetName": "Oak St.",
//		            "streetNum": "65157",
//		            "zipCode": "72734"
//		        },
//		        "addressId": 4,
//		        "customerFirstName": "Hayden",
//		        "customerId": 4,
//		        "customerLastName": "Herrera",
//		        "email": "spider-child@gmail.com",
//		        "phoneNumber": "5019876541"
//		    }
//		];
	
	$scope.getCustomers = function() {
		console.log('getCustomers()');
		
		$scope.customers = 
			[
				{
					"customerFirstName": "retrieving customers...",
					"phoneNumber": "0000000000"
				}
			];
		
		$http.get("/dgcateringapi/v1/catering/customers")
		.then(function(response) {
			$scope.customers = response.data;
			console.log('Number of Customers: ' + $scope.customers.length);
		}, function(response) {
			console.log('error HTTP GET Customers: ' + response.status);
		});
	};
	
//	$scope.addressTypes = ['Apartment', 'Business', 
//		'Hospital', 'School', 'House'];
	
	$scope.getAddressTypes = function() {
		console.log('getAddressTypes');
		
		console.log('addressTypes from database');
		
		$http.get("/dgcateringapi/v1/catering/addresstypes")
		.then(function(response) {
			$scope.addressTypes = response.data;
			console.log('Number of Address Types: ' + $scope.addressTypes.length);
		}, function(response) {
			console.log('error HTTP GET Address Types: ' + response.status);
		});
	};

	$scope.getAddressTypes();
//	$scope.getCompanySettings();
	$scope.getCustomers();
	
});

dgCateringApp.controller('stackScreenController', function($scope) {
	$scope.updateStackNavbar();
});

dgCateringApp.controller('resumeScreenController', function($scope) {
	$scope.updateResumeNavbar();
});

dgCateringApp.controller('dailyNotesScreenController', function($scope, $http) {
	$scope.showMainDailyNoteScreen = true;
	$scope.showDailyNoteDetailScreen = false;
	$scope.showCreateNewDailyNoteScreen = false;
	$scope.updateDailyNoteNavbar();
	
	$scope.viewNote = function(noteToView) {
		$scope.showMainDailyNoteScreen = false;
		$scope.showDailyNoteDetailScreen = true;
		$scope.showCreateNewDailyNoteScreen = false;
		$scope.noteToView = noteToView;
	};
	
	$scope.returnToMainDailyNoteScreen = function() {
		$scope.showMainDailyNoteScreen = true;
		$scope.showDailyNoteDetailScreen = false;
		$scope.showCreateNewDailyNoteScreen = false;
//		$scope.getDailyNotes();
	};
	
	$scope.goToNewDailyNote = function() {
		$scope.showMainDailyNoteScreen = false;
		$scope.showDailyNoteDetailScreen = false;
		$scope.showCreateNewDailyNoteScreen = true;
		$scope.clearNewDailyNote();
	};
	
	$scope.clearNewDailyNote = function() {
		$scope.newDailyNote = 
		{
			dailyNote: ''
		};
	};
	
	$scope.getDailyNotes = function() {
		console.log('getDailyNotes()');
		
		$scope.dailyNotes = [{"dailyNote": "Retrieving Notes..."}];
		
		$http.get("/dgcateringapi/v1/catering/dailynotes")
		.then(function(response) {
			$scope.dailyNotes = response.data;
			console.log('Number of Daily Notes: ' + $scope.dailyNotes.length);
		}, function(response) {
			console.log('error HTTP GET Daily Notes: ' + response.status);
		});
	};
	
	$scope.postNewDailyNote = function() {
		$scope.jsonObject = angular.toJson($scope.newDailyNote, false);
		console.log("new daily note :" + $scope.jsonObject);
		
//		send to back with post statement
		$http.post("/dgcateringapi/v1/catering/dailynotes/add_new", $scope.jsonObject)
		.then(
				function success(response) {
					console.log('status : ' + response.status);
//					$scope.createStatus = 'successful addition of new daily note';
//					$scope.successfulInsert = true;
					$scope.getDailyNotes();
				},
				function error(response) {
					console.log('error, return status: ' + response.status);
//					$scope.createStatus = 'insert error, ' + response.data.message;
				}
		);
		
		$scope.returnToMainDailyNoteScreen();
	};
	
	$scope.getDailyNotes();
	
//	$scope.dailyNotes = 
//	[
//	    {
//	        "createDateTime": "2019-06-30T18:48:23.244",
//	        "dailyNote": "called a bunch of folks.",
//	        "dailyNoteId": 1
//	    },
//	    {
//	        "createDateTime": "2019-06-30T18:48:23.244",
//	        "dailyNote": "ate a bunch of pizza",
//	        "dailyNoteId": 2
//	    }
//	];
});

dgCateringApp.controller('customerScreenController', function($scope, $http) {
	$scope.showMainCustomerScreen = true;
	$scope.showCustomerDetailScreen = false;
	$scope.isCustomerUpdateButtonDisabled = false;
	$scope.updateCustNavbar();
	
	$scope.updateCustomerScreen = function(customerToUpdate) {
		$scope.showMainCustomerScreen = false;
		$scope.showCustomerDetailScreen = true;
//		$scope.customerToUpdate = customerToUpdate;
		$scope.customerToUpdate = angular.copy(customerToUpdate);
	};
	
	$scope.returnToAllCustomers = function() {
//		$scope.getCustomers();
		$scope.showMainCustomerScreen = true;
		$scope.showCustomerDetailScreen = false;
		$scope.customerSearch = '';
	};
	
	$scope.saveCustomer = function() {
		if($scope.customerToUpdate.customerId == 0)
		{
			$scope.postCustomer();
		}
		else
		{
			$scope.putCustomer();
		}
//		$scope.getCustomers();
		
//		setTimeout($scope.returnToAllCustomers(), 30000);
		
		$scope.returnToAllCustomers();
		
	};
	
	$scope.putCustomer = function() {
		console.log("put customer");
		$scope.customerJsonObj = angular.toJson($scope.customerToUpdate, false);
		console.log("update customer: " + $scope.customerJsonObj);
		
		$http.put("/dgcateringapi/v1/catering/customers/update", $scope.customerJsonObj)
		.then(
			function success(response) {
				console.log('status: ' + response.status);
				$scope.getCustomers();				
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
			}
		);
	};
	
	$scope.postCustomer = function() {
		console.log("postCustomer");
		$scope.customerJsonObj = angular.toJson($scope.customerToUpdate, false);
		console.log("new customer :" + $scope.customerJsonObj);
				
//		send to back with post statement
		$http.post("/dgcateringapi/v1/catering/customers/add_new", $scope.customerJsonObj)
		.then(
			function success(response) {
				console.log('status : ' + response.status);
				$scope.getCustomers();
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
			}
		);
	};
	
	$scope.getCustomers();
});


dgCateringApp.controller('addressScreenController', function($scope, $http) {
	$scope.showMainAddressScreen = true;
	$scope.showAddressDetailScreen = false;
	$scope.dueDateWarning = "";
	
	$scope.updateAddressScreen = function(addressToUpdate) {
		$scope.showMainAddressScreen = false;
		$scope.showAddressDetailScreen = true;
		$scope.addressToUpdate = angular.copy(addressToUpdate);
		console.log('update address screen: ' + angular.toJson($scope.addressToUpdate));
	};
	
	$scope.returnToAllAddresses = function() {
		$scope.showMainAddressScreen = true;
		$scope.showAddressDetailScreen = false;
//		$scope.getAddresses();
	};
		
	$scope.getAddresses = function() {
		console.log('getAddresses()');
		
		$scope.addresses = [{"businessName": "Retrieving Addresses..."}];
		
		$http.get("/dgcateringapi/v1/catering/addresses/")
		.then(function(response) {
			$scope.addresses = response.data;
			console.log('Number of Addresses: ' + $scope.addresses.length);
		}, function(response) {
			console.log('error HTTP GET Addresses: ' + response.status);
		});
	};
	
	$scope.saveAddress = function() {
		if($scope.addressToUpdate.addressId == 0)
		{
			$scope.postAddress();
		}
		else
		{
			$scope.putAddress();
		}
		
//		$scope.getAddresses();
		$scope.returnToAllAddresses();
	};
	
	$scope.putAddress = function() {
		console.log('putAddress()');
		$scope.jsonObject = angular.toJson($scope.addressToUpdate, false);
		console.log('update address: ' + $scope.jsonObject);
		
		$http.put("/dgcateringapi/v1/catering/addresses/update", $scope.jsonObject)
		.then(
			function success(response) {
				console.log('status: ' + response.status);
				$scope.getAddresses();
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
				$scope.updateStatus = 'update error, ' + response.data.messae;
			}
		);
	};
	
	$scope.postAddress = function() {
		console.log('postAddress()');
		$scope.jsonObject = angular.toJson($scope.addressToUpdate, false);
		console.log('add new address: ' + $scope.jsonObject);
				
//		send to back with post statement
		$http.post("/dgcateringapi/v1/catering/addresses/add_new", $scope.jsonObject)
		.then(
			function success(response) {
				console.log('status : ' + response.status);
				$scope.getAddresses();
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
			}
		);
	};
	
	$scope.getAddresses();
	
//	$scope.addresses = 
//	[
//	    {
//	        "addressId": 1,
//	        "addressNote": "Number 2 has taken over number 1",
//	        "addressType": "Business",
//	        "addressTypeAbr": "Suite",
//	        "businessName": "Damgoode Pies #2",
//	        "city": "Little Rock",
//	        "roomNum": 100,
//	        "state": "AR",
//	        "streetName": "Cantrell Rd.",
//	        "streetNum": "6706",
//	        "zipCode": "72207"
//	    },
//	    {
//	        "addressId": 2,
//	        "addressNote": "oh durango",
//	        "addressType": "Apartment",
//	        "addressTypeAbr": "Apt",
//	        "businessName": "McDonalds",
//	        "city": "Durango",
//	        "roomNum": 100,
//	        "state": "CO",
//	        "streetName": "Forever Rd.",
//	        "streetNum": "1000",
//	        "zipCode": "46821"
//	    },
//	    {
//	        "addressId": 3,
//	        "addressNote": "The Vern!",
//	        "addressType": "House",
//	        "addressTypeAbr": "",
//	        "city": "Little Rock",
//	        "roomNum": 0,
//	        "state": "AR",
//	        "streetName": "Vernon Ave.",
//	        "streetNum": "300",
//	        "zipCode": "72205"
//	    },
//	    {
//	        "addressId": 4,
//	        "addressNote": "added address",
//	        "addressType": "School",
//	        "addressTypeAbr": "Room",
//	        "businessName": "Arkansas Coding Academy",
//	        "city": "Conway",
//	        "roomNum": 100,
//	        "state": "AR",
//	        "streetName": "Oak St.",
//	        "streetNum": "65157",
//	        "zipCode": "72734"
//	    }
//	];
});



dgCateringApp.controller('orderScreenController', function($scope, $http) {
	$scope.showOrderScreen = true;
	$scope.showOrderDetailScreen = false;
	$scope.showOrderItemHomeScreen = false;
	$scope.showOrderItemUpdateScreen = false;
	$scope.showAddOrderDiscountScreen = false;
	$scope.showAddDealScreen = false;
	$scope.orderToUpdate = $scope.emptyOrder;
	$scope.updateOrderNavbar();
	
	$scope.bothSelectorDisabled = false;
	$scope.paidSelectorDisabled = false;
	$scope.unpaidSelectorDisabled = true;
	$scope.currentIsPaidSetting = 'Unpaid';

	$scope.addUpdateOrderCustomerButtonDisabled = true;
	$scope.searchOrderCustomerButtonDisabled = false;
	$scope.showAddUpdateOrderCustomerScreen = true;
	$scope.showSearchOrderCustomerScreen = false;
	
//	Shows the screen that allows the user to add a flat
//	percentage or dollar amount discount to the order.
	$scope.goToAddOrderDiscountScreen = function() {
		console.log("go to add order discount screen");
		$scope.showOrderItemHomeScreen = false;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = true;
		$scope.showAddDealScreen = false;
	};
	
//	Handles adding the discount to the ticket by including
//	it in the subtotal calculation, adding the row to the ticket
//	and changing the screens back to the OrderItemHomeScreen.
	$scope.saveDiscount = function() {
		if($scope.orderToUpdate.percentageDiscountRate != 0)
		{
			$scope.orderToUpdate.hasPercentageDiscount = true;
		}
		else
		{
			$scope.orderToUpdate.percentageDiscountAmt = 0;
			$scope.orderToUpdate.hasPercentageDiscount = false;
		}
		
		if($scope.orderToUpdate.dollarDiscountAmt != 0)
		{
			$scope.orderToUpdate.hasDollarDiscount = true;
		}
		else
		{
			$scope.orderToUpdate.hasDollarDiscount = false;
		}
		
		$scope.updateTotals();
		$scope.goToOrderItemHomeScreen();		
	};
	
	$scope.removeDiscounts = function() {
		$scope.orderToUpdate.percentageDiscountRate = 0;
		$scope.orderToUpdate.dollarDiscountAmt = 0;
		$scope.orderToUpdate.percentageDiscountAmt = 0;
				
		$scope.orderToUpdate.hasPercentageDiscount = false;
		$scope.orderToUpdate.hasDollarDiscount = false;
		$scope.updateTotals();
		$scope.goToOrderItemHomeScreen();
	};
	
	$scope.goToOrderItemHomeScreen = function() {
		$scope.showOrderScreen = false;
		$scope.showOrderDetailScreen = true;
		$scope.showOrderItemHomeScreen = true;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.showAddDealScreen = false;
	};
	
	$scope.goToAddDealScreen = function() {
		$scope.showOrderItemHomeScreen = false;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.showAddDealScreen = true;
		
		$scope.checkForAvailableDeals();	
	};
	
	$scope.checkForAvailableDeals = function() {
		$scope.notEnoughPizza = false;
		$scope.notEnoughPizzaWithoutDeal = false;
		$scope.fiveForHundredDealAvailable = false;
		$scope.mightyFiveDealAvailable = false;
		
		var countTotalPizzas = 0;
		var countPizzasWithoutDeal = 0;
		var numCheeseNoDeal = 0;
		var numTier1NoDeal = 0;
		var numTier2NoDeal = 0;
		var num1ToppingNoDeal = 0;
		for(item of $scope.orderToUpdate.items)
		{
			if(item.menuItem.menuGroup == "Pizza" &&
				item.markedToRemoveFromOrder == false)
			{
				countTotalPizzas++;
			}
			
			if(item.menuItem.menuGroup == "Pizza" &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				countPizzasWithoutDeal++;
			}
			
			if(item.menuItem.dealCategory == "Cheese" &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				numCheeseNoDeal++;
			}
			else if(item.menuItem.dealCategory == "Tier1" &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				numTier1NoDeal++;
			}
			else if(item.menuItem.dealCategory == "Tier2" &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				numTier2NoDeal++;
			}
			else if(item.menuItem.dealCategory == "1Topping" &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				num1ToppingNoDeal++;
			}
		}
		
		console.log("countTotalPizzas: " + countTotalPizzas);
		console.log("countPizzasWithoutDeal: " + countPizzasWithoutDeal);
		console.log("numCheeseNoDeal: " + numCheeseNoDeal);
		console.log("numTier1NoDeal: " + numTier1NoDeal);
		console.log("numTier2NoDeal: " + numTier2NoDeal);
		console.log("num1ToppingNoDeal: " + num1ToppingNoDeal);
		
		if(countTotalPizzas < 5)
		{
			$scope.notEnoughPizza = true;
		}
		
		if(countPizzasWithoutDeal < 5 && 
			$scope.notEnoughPizza == false)
		{
			$scope.notEnoughPizzaWithoutDeal = true;
		}
		
		if(num1ToppingNoDeal >= 5)
		{
			$scope.fiveForHundredDealAvailable = true;
		}
		
		if(numCheeseNoDeal >= 1 &&
			numTier1NoDeal >= 2 &&
			numTier2NoDeal >= 2)
		{
			$scope.mightyFiveDealAvailable = true;
		}	
	};
	
	$scope.addMightyFiveDeal = function() {
		console.log("addMightyFiveDeal");
		
		var numCheeseOrdered = 0;
		var numTier1Ordered = 0;
		var numTier2Ordered = 0;
		
		for(item of $scope.orderToUpdate.items)
		{
			
			if(numCheeseOrdered == 0 &&
				item.menuItem.dealCategory == 'Cheese' &&
				item.hasDealApplied == false &&
				item.markedToRemoveFromOrder == false)
			{
				item.activePrice = 15;
				item.hasDealApplied = true;
				numCheeseOrdered++;
				console.log("cheese active price: " + item.activePrice);
			}
			else if(numTier1Ordered < 2 &&
					item.menuItem.dealCategory == 'Tier1' &&
					item.hasDealApplied == false &&
					item.markedToRemoveFromOrder == false)
			{
				item.activePrice = 22.5;
				item.hasDealApplied = true;
				numTier1Ordered++;
				console.log("Tier1 activePrice: " + item.activePrice);
			}
			else if(numTier2Ordered < 2 &&
					item.menuItem.dealCategory == 'Tier2' &&
					item.hasDealApplied == false &&
					item.markedToRemoveFromOrder == false)
			{
				item.activePrice = 32.5;
				item.hasDealApplied = true;
				numTier2Ordered++;
				console.log("Tier2 activePrice: " + item.activePrice);
			}
		}
		
		$scope.orderToUpdate.hasDealsApplied = true;
		$scope.updateTotals();
		$scope.goToOrderItemHomeScreen();
	};
	
	$scope.addFiveForHundredDeal = function() {
		console.log("addFiveForHundredDeal");
		
		var num1ToppingOrdered = 0;
		
		for(item of $scope.orderToUpdate.items)
		{
			
			if(num1ToppingOrdered < 5 &&
					item.menuItem.dealCategory == '1Topping' &&
					item.hasDealApplied == false &&
					item.markedToRemoveFromOrder == false)
			{
				item.activePrice = 20;
				item.hasDealApplied = true;
				num1ToppingOrdered++;
				console.log("1Topping activePrice: " + item.activePrice);
			}
			
		}
		
		$scope.orderToUpdate.hasDealsApplied = true;
		$scope.updateTotals();
		$scope.goToOrderItemHomeScreen();	
	};
	
	$scope.returnToOrderScreen = function() {
		$scope.showOrderScreen = true;
		$scope.showOrderDetailScreen = false;
		$scope.showOrderItemHomeScreen = false;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.showAddDealScreen = false;
	};
	
	$scope.showPaidOrders = function() {
		$scope.bothSelectorDisabled = false;
		$scope.paidSelectorDisabled = true;
		$scope.unpaidSelectorDisabled = false;
		$scope.currentIsPaidSetting = 'Paid';
		$scope.search.isPaid = 'true';
	};
	
	$scope.showUnpaidOrders = function() {
		$scope.bothSelectorDisabled = false;
		$scope.paidSelectorDisabled = false;
		$scope.unpaidSelectorDisabled = true;
		$scope.currentIsPaidSetting = 'Unpaid';
		$scope.search.isPaid = 'false';
	};
	
	$scope.showBothOrders = function() {
		$scope.bothSelectorDisabled = true;
		$scope.paidSelectorDisabled = false;
		$scope.unpaidSelectorDisabled = false;
		$scope.currentIsPaidSetting = 'Paid/Unpaid';
		$scope.search.isPaid = '';
	};
	
	$scope.showOrderListPaidCheckX = function(order) {
		if(order.isPaid)
		{
			return 'fas fa-check-circle limeGreen';
		}
		else
		{
			return 'fas fa-times-circle text-dgp-red';
		}
	};
	
	$scope.showOrderListInPosCheckX = function(order) {
		if(order.isInPos)
		{
			return 'fas fa-check-circle limeGreen';
		}
		else
		{
			return 'fas fa-times-circle text-dgp-red';
		}
	};
	
	$scope.addUpdateOrderCustomer = function() {
		$scope.orderCustomerToUpdate = angular.copy($scope.orderToUpdate.customer);
		$scope.goToAddUpdateOrderCustomerScreen();
	};
	
	$scope.goToAddUpdateOrderCustomerScreen = function() {
		$scope.addUpdateOrderCustomerButtonDisabled = true;
		$scope.searchOrderCustomerButtonDisabled = false;
		$scope.showAddUpdateOrderCustomerScreen = true;
		$scope.showSearchOrderCustomerScreen = false;
	};
	
	$scope.searchOrderCustomer = function() {
		$scope.addUpdateOrderCustomerButtonDisabled = false;
		$scope.searchOrderCustomerButtonDisabled = true;
		$scope.showAddUpdateOrderCustomerScreen = false;
		$scope.showSearchOrderCustomerScreen = true;
		$scope.customerSearch = '';
		
	};
	
	$scope.sendOrderCustomerToAddUpdate = function(customer) {
		$scope.orderCustomerToUpdate = angular.copy(customer);
		$scope.goToAddUpdateOrderCustomerScreen();
	};
	
	$scope.clearOrderCustomer = function() {
		$scope.orderCustomerToUpdate = angular.copy($scope.emptyCustomer);
	};
	
	$scope.saveUpdatedOrderCustomer = function() {
		$scope.orderToUpdate.customer = angular.copy($scope.orderCustomerToUpdate);
	};
	
	$scope.saveNewDateTime = function() {
		console.log('save new date time');
		
		$scope.fixDueDateString();
		
		console.log('dueDateTime: ' + angular.toJson($scope.dueDateTime));
		console.log('orderDueDateTime: ' + angular.toJson($scope.orderToUpdate.orderDueDateTime));
	};
	
	$scope.updateOrder = function(orderToUpdate) {
		
		console.log("update order: " + orderToUpdate.orderId);
		$scope.orderToUpdate = angular.copy(orderToUpdate);
		$scope.orderJson = angular.toJson($scope.orderToUpdate);
		console.log('orderToUpdate: ' + $scope.orderJson);
		$scope.updateTotals();
		$scope.showOrderScreen = false;
		$scope.showOrderDetailScreen = true;
		$scope.showOrderItemHomeScreen = true;
		$scope.setBooleanButtons();
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.dueDateTime = new Date($scope.orderToUpdate.orderDueDateTime);
	};
	
	$scope.updateItem = function(itemToUpdate) {
		$scope.itemToUpdate = itemToUpdate;
		$scope.disableDeleteButton = false;
		
		for(var i = 0; i < $scope.availableMenuItems.length; i++)
		{
			if($scope.itemToUpdate.menuItem.itemName == $scope.availableMenuItems[i].itemName)
			{
				$scope.itemToUpdate.menuItem = $scope.availableMenuItems[i];
			}
		}
		
		console.log('update item (from scope): ' + $scope.itemToUpdate.menuItem.itemName);
		$scope.showOrderItemHomeScreen = false;
		$scope.showOrderItemUpdateScreen = true;
	
	};
	
	$scope.saveOrder = function() {
		if($scope.orderToUpdate.orderId == 0)
		{
			$scope.postOrder();
		}
		else
		{
			$scope.putOrder();
		}
		
		$scope.returnToOrderScreen();
	};
	
	$scope.fixDueDateString = function() {
		console.log('fixDueDateString()');
		var year = $scope.dueDateTime.getFullYear();
		var month, day, hour, minutes;
		console.log('year: ' + year);
		
		if(($scope.dueDateTime.getMonth() + 1) < 10)
		{
			month = '0' + ($scope.dueDateTime.getMonth() + 1);
		}
		else
		{
			month = ($scope.dueDateTime.getMonth() + 1);
		}
		console.log('month: ' + month);
		
		if($scope.dueDateTime.getDate() < 10)
		{
			day = '0' + $scope.dueDateTime.getDate();
		}
		else
		{
			day = $scope.dueDateTime.getDate();
		}
		console.log('day: ' + day);
		
		if($scope.dueDateTime.getHours() < 10)
		{
			hour = '0' + $scope.dueDateTime.getHours();
		}
		else
		{
			hour = $scope.dueDateTime.getHours();
		}
		console.log('hour: ' + hour);
		
		if($scope.dueDateTime.getMinutes() < 10)
		{
			minutes = '0' + $scope.dueDateTime.getMinutes()
		}
		else
		{
			minutes = $scope.dueDateTime.getMinutes();
		}
		console.log('minutes: ' + minutes);
		
		$scope.orderToUpdate.orderDueDateTime = year + '-' + month + '-' + day +
			'T' + hour + ':' + minutes + ':00';
		
		console.log('$scope.orderToUpdate.orderDueDateTime: ' + 
			angular.toJson($scope.orderToUpdate.orderDueDateTime));
		
	};
	
	$scope.postOrder = function() {
		console.log('postOrder()');
		
		$scope.jsonObject = angular.toJson($scope.orderToUpdate, false);
		console.log('Order to send to SQL: ' + $scope.jsonObject);
					
//		send to back with post statement
		$http.post("/dgcateringapi/v1/catering/orders/add_new", $scope.jsonObject)
		.then(
			function success(response) {
				console.log('status : ' + response.status);
				$scope.getCustomers();
				$scope.getOrders();
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
			}
		);	
	};
	
	$scope.putOrder = function() {
		console.log('putOrder()');
		$scope.jsonObject = angular.toJson($scope.orderToUpdate, false);
		console.log('Order to send to SQL: ' + $scope.jsonObject);
				
		$http.put("/dgcateringapi/v1/catering/orders/update", $scope.jsonObject)
		.then(
			function success(response) {
				console.log('status: ' + response.status);
				$scope.getCustomers();
				$scope.getOrders();
			},
			function error(response) {
				console.log('error, return status: ' + response.status);
			}
		);	
	};
	
	$scope.startNewOrder = function() {
		console.log('Start new Order');
	};
	
	$scope.datePeriod = function(date2) {
		dt1 = new Date();
		dt2 = new Date(date2);
		var period = Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), 
				dt2.getDate()) - Date.UTC(dt1.getFullYear(), dt1.getMonth(), 
				dt1.getDate()) ) /(1000 * 60 * 60 * 24));
		if(period < 7)
		{
			$scope.dueDateWarning = "text-dgp-red";
		}
		else
		{
			$scope.dueDateWarning = "";
		}
		
		return period;
	};
	
	$scope.addNewItemToOrder = function() {
		console.log('add new item to order');
		console.log('item going into addNewItemToOrder: ' + angular.toJson($scope.itemToUpdate));
		$scope.showOrderItemHomeScreen = false;
		$scope.showOrderItemUpdateScreen = true;
		$scope.showAddOrderDiscountScreen = false;
		$scope.disableDeleteButton = true;
		$scope.clearItemToUpdate();
	};
	
	$scope.clearItemToUpdate = function () {
		$scope.itemToUpdate = 
			{
                activePrice : 0,
                crust : '',
                hasDealApplied : false,
                itemNote : '',
                markedToRemoveFromOrder : false,
                menuItem : {
                    dealCategory : '',
                    itemName : '',
                    menuGroup : '',
                    menuItemId : 0,
                    menuPrice : 0,
                    size : ''
                },
                orderId : $scope.orderToUpdate.orderId,
                orderItemId : 0,
                sauce : ''
            };
	};
	
//	$scope.orders = 
//		[
//		    {
//		        "cateringFee": 0,
//		        "customer": {
//		            "address": {
//		                "addressId": 101,
//		                "addressNote": "so tasty",
//		                "addressType": "House",
//		                "addressTypeAbr": "",
//		                "businessName": "Damgoode Pies #2",
//		                "city": "Little Rock",
//		                "roomNum": 0,
//		                "state": "AR",
//		                "streetName": "Cantrell Rd.",
//		                "streetNum": "6706",
//		                "zipCode": "72207"
//		            },
//		            "addressId": 101,
//		            "customerFirstName": "Roddy",
//		            "customerId": 101,
//		            "customerLastName": "Crawford",
//		            "customerNote": "Roddy is the greatest",
//		            "email": "roddy@damgoode.com",
//		            "phoneNumber": "5013520642"
//		        },
//		        "customerId": 101,
//		        "deliveryFee": 4,
//		        "dollarDiscountAmt": 0,
//		        "hasDealsApplied": false,
//		        "hasDeliveryFee": true,
//		        "hasDollarDiscount": false,
//		        "hasPercentageDiscount": true,
//		        "isBilledOrder": false,
//		        "isBuffetOrder": true,
//		        "isInPos": false,
//		        "isPaid": false,
//		        "isTaxExempt": false,
//		        "isVoid": false,
//		        "items": [
//		            {
//		                "activePrice": 18.99,
//		                "crust": "HandTossed",
//		                "hasDealApplied": false,
//		                "itemNote": "funk tastic",
//		                "markedToRemoveFromOrder": false,
//		                "menuItem": {
//		                    "dealCategory": "Cheese",
//		                    "itemName": "Cheese",
//		                    "menuGroup": "Pizza",
//		                    "menuItemId": 101,
//		                    "menuPrice": 18.99,
//		                    "size": "18"
//		                },
//		                "orderId": 1,
//		                "orderItemId": 101,
//		                "sauce": "Original Red"
//		            },
//		            {
//		                "activePrice": 24.49,
//		                "crust": "Thin",
//		                "hasDealApplied": false,
//		                "itemNote": "mad man in a box",
//		                "markedToRemoveFromOrder": false,
//		                "menuItem": {
//		                    "dealCategory": "Tier1",
//		                    "itemName": "Artie",
//		                    "menuGroup": "Pizza",
//		                    "menuItemId": 102,
//		                    "menuPrice": 24.49,
//		                    "size": "18"
//		                },
//		                "orderId": 1,
//		                "orderItemId": 102,
//		                "sauce": "Pink"
//		            }
//		        ],
//		        "numPeopleToFeed": 25,
//		        "orderCreateDateTime": "2019-07-23T01:52:34.257",
//		        "orderDueDateTime": "2019-07-31T01:00",
//		        "orderId": 1,
//		        "orderNote": "Do 10 jumping jacks",
//		        "paymentType": "cash",
//		        "percentageDiscountAmt": 21.74,
//		        "percentageDiscountRate": 50,
//		        "pricePerHead": 13,
//		        "subtotal": 21.74,
//		        "taxAmount": 4.78,
//		        "total": 30.52,
//		        "voidReason": ""
//		    },
//		    {
//		        "cateringFee": 9.2,
//		        "customer": {
//		            "address": {
//		                "addressId": 102,
//		                "addressNote": "fresh and magical",
//		                "addressType": "Business",
//		                "addressTypeAbr": "Suite",
//		                "businessName": "Damgoode Pies #1",
//		                "city": "Little Rock",
//		                "roomNum": 110,
//		                "state": "AR",
//		                "streetName": "Kavanaugh Blvd.",
//		                "streetNum": "2701",
//		                "zipCode": "72205"
//		            },
//		            "addressId": 102,
//		            "customerFirstName": "Laura",
//		            "customerId": 102,
//		            "customerLastName": "Crawford",
//		            "customerNote": "Laura is also the greatest",
//		            "email": "lkhope89@gmail.com",
//		            "phoneNumber": "5016281709"
//		        },
//		        "customerId": 102,
//		        "deliveryFee": 0,
//		        "dollarDiscountAmt": 10,
//		        "hasDealsApplied": false,
//		        "hasDeliveryFee": false,
//		        "hasDollarDiscount": true,
//		        "hasPercentageDiscount": false,
//		        "isBilledOrder": true,
//		        "isBuffetOrder": false,
//		        "isInPos": true,
//		        "isPaid": false,
//		        "isTaxExempt": true,
//		        "isVoid": false,
//		        "items": [
//		            {
//		                "activePrice": 36.99,
//		                "crust": "HandTossed",
//		                "hasDealApplied": false,
//		                "itemNote": "it's a pizza-pie",
//		                "markedToRemoveFromOrder": false,
//		                "menuItem": {
//		                    "dealCategory": "Tier2",
//		                    "itemName": "BBQ Chicken",
//		                    "menuGroup": "Pizza",
//		                    "menuItemId": 103,
//		                    "menuPrice": 36.99,
//		                    "size": "18"
//		                },
//		                "orderId": 2,
//		                "orderItemId": 103,
//		                "sauce": "Pesto"
//		            },
//		            {
//		                "activePrice": 54.99,
//		                "crust": "Thin",
//		                "hasDealApplied": false,
//		                "itemNote": "boop",
//		                "markedToRemoveFromOrder": false,
//		                "menuItem": {
//		                    "dealCategory": "",
//		                    "itemName": "Caesar Salad",
//		                    "menuGroup": "Salad",
//		                    "menuItemId": 104,
//		                    "menuPrice": 54.99,
//		                    "size": "Catering"
//		                },
//		                "orderId": 2,
//		                "orderItemId": 104,
//		                "sauce": "Original Red"
//		            }
//		        ],
//		        "numPeopleToFeed": 50,
//		        "orderCreateDateTime": "2019-07-23T01:52:34.257",
//		        "orderDueDateTime": "2019-08-23T01:52:34.257",
//		        "orderId": 2,
//		        "orderNote": "go around back",
//		        "paymentType": "Credit Card",
//		        "percentageDiscountAmt": 0,
//		        "percentageDiscountRate": 0,
//		        "pricePerHead": 0,
//		        "subtotal": 81.98,
//		        "taxAmount": 0,
//		        "total": 91.18,
//		        "voidReason": ""
//		    },
//		    {
//		        "cateringFee": 0,
//		        "customer": {
//		            "address": {
//		                "addressId": 103,
//		                "addressNote": "fancy patio",
//		                "addressType": "Apartment",
//		                "addressTypeAbr": "Apt",
//		                "businessName": "Damgoode Pies #6",
//		                "city": "Little Rock",
//		                "roomNum": 105,
//		                "state": "AR",
//		                "streetName": "President Clinton Ave.",
//		                "streetNum": "500",
//		                "zipCode": "72201"
//		            },
//		            "addressId": 103,
//		            "customerFirstName": "Hayden",
//		            "customerId": 103,
//		            "customerLastName": "Koon",
//		            "customerNote": "Hayden is pretty great",
//		            "email": "littleguy@gmail.com",
//		            "phoneNumber": "5016642239"
//		        },
//		        "customerId": 103,
//		        "deliveryFee": 4,
//		        "dollarDiscountAmt": 0,
//		        "hasDealsApplied": false,
//		        "hasDeliveryFee": true,
//		        "hasDollarDiscount": false,
//		        "hasPercentageDiscount": false,
//		        "isBilledOrder": false,
//		        "isBuffetOrder": false,
//		        "isInPos": false,
//		        "isPaid": true,
//		        "isTaxExempt": false,
//		        "isVoid": false,
//		        "items": [
//		            {
//		                "activePrice": 36.99,
//		                "crust": "Thin",
//		                "hasDealApplied": false,
//		                "itemNote": "blop",
//		                "markedToRemoveFromOrder": false,
//		                "menuItem": {
//		                    "dealCategory": "Tier2",
//		                    "itemName": "BBQ Chicken",
//		                    "menuGroup": "Pizza",
//		                    "menuItemId": 103,
//		                    "menuPrice": 36.99,
//		                    "size": "18"
//		                },
//		                "orderId": 3,
//		                "orderItemId": 105,
//		                "sauce": "Original Red"
//		            }
//		        ],
//		        "numPeopleToFeed": 100,
//		        "orderCreateDateTime": "2019-07-23T01:52:34.257",
//		        "orderDueDateTime": "2019-06-23T01:52:34.257",
//		        "orderId": 3,
//		        "orderNote": "be there by 6",
//		        "paymentType": "Credit Card",
//		        "percentageDiscountAmt": 0,
//		        "percentageDiscountRate": 0,
//		        "pricePerHead": 0,
//		        "subtotal": 36.99,
//		        "taxAmount": 4.07,
//		        "total": 45.06,
//		        "voidReason": ""
//		    }
//		];
	
	$scope.getOrders = function() {
		console.log('getOrders()');
		
		$scope.orders = 
			[
				{
					"customer": {
			            "customerFirstName": "Retrieving Orders...",
			            "phoneNumber": "0000000000"
			        }
				}
			];
		
		$http.get("/dgcateringapi/v1/catering/orders")
		.then(function(response) {
			$scope.orders = response.data;
			console.log('Number of Orders: ' + $scope.orders.length);
		}, function(response) {
			console.log('error HTTP GET Orders: ' + response.status);
		});
	};
	
//	$scope.addressTypes = ['Apartment', 'Business', 
//		'Hospital', 'School', 'House'];
	
	$scope.getAddressTypes = function() {
		console.log('getAddressTypes');
		
		console.log('addressTypes from database');
		
		$http.get("/dgcateringapi/v1/catering/addresstypes")
		.then(function(response) {
			$scope.addressTypes = response.data;
			console.log('Number of Address Types: ' + $scope.addressTypes.length);
		}, function(response) {
			console.log('error HTTP GET Address Types: ' + response.status);
		});
	};
	
	$scope.getAvailableMenuItems = function () {
		console.log('getAvailableMenuItems()');
		
		$http.get("/dgcateringapi/v1/catering/menuitems/retrieve")
		.then(function(response) {
			$scope.availableMenuItems = response.data;
			console.log('Number of MenuItems: ' + $scope.availableMenuItems.length);
		}, function(response) {
			console.log('error HTTP GET Available Menu Items: ' + response.status);
		});
	};
	
//	Sets the color of the boolean buttons when the orderdetail
//	screen loads
	$scope.setBooleanButtons = function() {
		console.log('setBooleanButtons');
		
		$scope.setBilledBooleanColor();
		$scope.setDeliveryBooleanColor();
		$scope.setTaxExemptBooleanColor();
		$scope.setPaidBooleanColor();
		$scope.setInPosBooleanColor();
		$scope.setBuffetBooleanColor();
	};
	
//	Sets the color of the inPOS Boolean Button
	$scope.setInPosBooleanColor = function() {
		$scope.inPosButtonIsActive = 'primary';
		if($scope.orderToUpdate.isInPos)
		{
			$scope.inPosButtonIsActive = 'success';
		}
	};
	
//	Sets the color of the Paid Boolean Button
	$scope.setPaidBooleanColor = function() {
		$scope.paidButtonIsActive = 'primary';
		if($scope.orderToUpdate.isPaid)
		{
			$scope.paidButtonIsActive = 'success';
		}
	};
	
//	Sets the color of the Tax Exempt Boolean Button
	$scope.setTaxExemptBooleanColor = function() {
		$scope.taxExemptButtonIsActive = 'primary';
		if($scope.orderToUpdate.isTaxExempt)
		{
			$scope.taxExemptButtonIsActive = 'success';
		}
	};
	
//	Sets the color of the Delivery Boolean Button
	$scope.setDeliveryBooleanColor = function() {
		$scope.deliveryButtonIsActive = 'primary';
		if($scope.orderToUpdate.hasDeliveryFee)
		{
			$scope.deliveryButtonIsActive = 'success';
		}
	};
	
//	Sets the color of the Billed Boolean Button
	$scope.setBilledBooleanColor = function() {
		$scope.billedIsActive = 'primary';
		if($scope.orderToUpdate.isBilledOrder)
		{
			$scope.billedIsActive = 'success';
		}
	};
	
//	Sets the color of the Buffet Boolean Button
	$scope.setBuffetBooleanColor = function() {
		$scope.buffetIsActive = 'primary';
		$scope.disableAddDealButton = false;
		$scope.disableOrderDiscountButton = false;
		
		if($scope.orderToUpdate.isBuffetOrder)
		{
			$scope.buffetIsActive = 'success';
			$scope.disableAddDealButton = true;
			$scope.disableOrderDiscountButton = true;
			$scope.removeDiscounts();
		}
	};
	
//	Makes a button that works sort of like a checkbox
//	This one handles the Buffet Order Boolean button
	$scope.buffetOrderBooleanButton = function() {
		console.log('Buffet Button at start: ' + $scope.orderToUpdate.isBuffetOrder);
		if($scope.orderToUpdate.isBuffetOrder)
		{
			$scope.orderToUpdate.isBuffetOrder = false;
			$scope.buffetIsActive = 'primary';
			$scope.disableAddDealButton = false;
			$scope.disableOrderDiscountButton = false;
		}
		else
		{
			$scope.orderToUpdate.isBuffetOrder = true;
			$scope.buffetIsActive = 'success';
			$scope.disableAddDealButton = true;
			$scope.disableOrderDiscountButton = true;
			$scope.removeDiscounts();
		}
		console.log('Buffet Button at end: ' + $scope.orderToUpdate.isBuffetOrder);
		$scope.updateTotals();
	}
	
//	Makes a button that works sort of like a checkbox
//	This one handles the in POS Boolean button
	$scope.inPosBooleanButton = function() {
		console.log('In POS at start: ' + $scope.orderToUpdate.isInPos);
		if($scope.orderToUpdate.isInPos)
		{
			$scope.orderToUpdate.isInPos = false;
			$scope.inPosButtonIsActive = 'primary';
		}
		else
		{
			$scope.orderToUpdate.isInPos = true;
			$scope.inPosButtonIsActive = 'success';
		}
		console.log('In POS at end: ' + $scope.orderToUpdate.isInPos);
		$scope.updateTotals();
	}
	
//	Makes a button that works sort of like a checkbox
//	This one handles the Paid Boolean button
	$scope.paidBooleanButton = function() {
		console.log('Paid at start: ' + $scope.orderToUpdate.isPaid);
		if($scope.orderToUpdate.isPaid)
		{
			$scope.orderToUpdate.isPaid = false;
			$scope.paidButtonIsActive = 'primary';
			$scope.orderToUpdate.paymentType = '';
			
			if($scope.orderToUpdate.isBilledOrder)
			{
				$scope.billedOrderBooleanButton();
			}
		}
		else
		{
			$scope.orderToUpdate.isPaid = true;
			$scope.paidButtonIsActive = 'success';
		}
		console.log('Paid at end: ' + $scope.orderToUpdate.isPaid);
		$scope.updateTotals();
	};
	
//	Makes a button that works sort of like a checkbox
//	This one handles the Tax Exempt Boolean button
	$scope.taxExemptBooleanButton = function() {
		console.log('taxExempt at start: ' + $scope.orderToUpdate.isTaxExempt);
		if($scope.orderToUpdate.isTaxExempt)
		{
			$scope.orderToUpdate.isTaxExempt = false;
			$scope.taxExemptButtonIsActive = 'primary';
		}
		else
		{
			$scope.orderToUpdate.isTaxExempt = true;
			$scope.taxExemptButtonIsActive = 'success';
		}
		console.log('taxExempt at end: ' + $scope.orderToUpdate.isTaxExempt);
		$scope.updateTotals();
	};
	
//	Makes a button that works sort of like a checkbox
//	This one handles the delivery fee button
	$scope.deliveryFeeBooleanButton = function() {
		console.log('delivery fee at start: ' + $scope.orderToUpdate.hasDeliveryFee);
		if($scope.orderToUpdate.hasDeliveryFee)
		{
			$scope.orderToUpdate.hasDeliveryFee = false;
			$scope.deliveryButtonIsActive = 'primary';
		}
		else
		{
			$scope.orderToUpdate.hasDeliveryFee = true;
			$scope.deliveryButtonIsActive = 'success';
		}
		console.log('delivery fee at end: ' + $scope.orderToUpdate.hasDeliveryFee);
		$scope.updateTotals();
	};
	
//	Makes a button that works sort of like a checkbox
//	This one handles the billed order button
	$scope.billedOrderBooleanButton = function() {
		console.log('billedOrder at start: ' + $scope.orderToUpdate.isBilledOrder);
		if($scope.orderToUpdate.isBilledOrder)
		{
			$scope.orderToUpdate.isBilledOrder = false;
			$scope.billedIsActive = 'primary';
			
			if($scope.orderToUpdate.isPaid)
			{
				$scope.paidBooleanButton();
			}
		}
		else
		{
			$scope.orderToUpdate.isBilledOrder = true;
			$scope.billedIsActive = 'success';
			$scope.paidBooleanButton();
			$scope.orderToUpdate.paymentType = 'Account';
		}
		console.log('billedOrder at end: ' + $scope.orderToUpdate.isBilledOrder);
		$scope.updateTotals();
	};
	
//	Loops through the item list and figures all totals for the order
//	Initial values for tax rate and delivery fee come from the 
//	database.
	$scope.updateTotals = function() {
		console.log("updateTotals()");
		$scope.updateSubtotal();
		$scope.updateCateringFee();
		$scope.updateDeliveryFee();
		$scope.updateTaxAmount();
		$scope.updateOrderTotal();
	};

	$scope.setAllActivePricesToZero = function() {
		for(item of $scope.orderToUpdate.items)
		{
			item.activePrice = 0;
			item.hasDealApplied = false;
			$scope.orderToUpdate.hasDealsApplied = false;
		}
	};
	
	$scope.resetAllActivePrices = function() {
		for(item of $scope.orderToUpdate.items)
		{
			if(!item.markedToRemoveFromOrder)
			{
				item.activePrice = item.menuItem.menuPrice;
			}
			
			item.hasDealApplied = false;
		}
	};
	
	$scope.removeAllDeals = function() {
		$scope.resetAllActivePrices();
		$scope.orderToUpdate.hasDealsApplied = false;
		$scope.updateTotals();
		$scope.goToOrderItemHomeScreen();
	};

	$scope.updateSubtotal = function() {
		console.log("updateSubtotal()");
		if($scope.orderToUpdate.isBuffetOrder)
		{
			$scope.setAllActivePricesToZero();
			
			$scope.orderToUpdate.subtotal =
				$scope.orderToUpdate.numPeopleToFeed
				* $scope.orderToUpdate.pricePerHead;
			console.log("Is Buffet Order, subtotal: " + $scope.orderToUpdate.subtotal);
		}
		else
		{
			console.log("$scope.orderToUpdate.hasDealsApplied: " + $scope.orderToUpdate.hasDealsApplied);
			console.log("!$scope.orderToUpdate.hasDealsApplied: " + !$scope.orderToUpdate.hasDealsApplied);
			if(!$scope.orderToUpdate.hasDealsApplied)
			{
				$scope.resetAllActivePrices();
			}
			
			$scope.orderToUpdate.subtotal = 0;
			for(item of $scope.orderToUpdate.items)
			{
				$scope.orderToUpdate.subtotal 
					+= item.activePrice;
			}
		}
		
		$scope.orderToUpdate.percentageDiscountAmt = angular.copy($scope.orderToUpdate.subtotal) 
			* ($scope.orderToUpdate.percentageDiscountRate/100);
		console.log('$scope.orderToUpdate.percentageDiscountAmt: ' + $scope.orderToUpdate.percentageDiscountAmt);
		console.log('original Subtotal: ' + $scope.orderToUpdate.subtotal);
		$scope.orderToUpdate.subtotal = 
			$scope.orderToUpdate.subtotal 
			- $scope.orderToUpdate.percentageDiscountAmt 
			- $scope.orderToUpdate.dollarDiscountAmt;
		console.log('subtotal after discount: ' + $scope.orderToUpdate.subtotal);
	};
	
	$scope.updateOrderTotal = function () {
		$scope.orderToUpdate.total =
			$scope.orderToUpdate.subtotal +
			$scope.orderToUpdate.deliveryFee +
			$scope.orderToUpdate.cateringFee +
			$scope.orderToUpdate.taxAmount;
	};
	
	$scope.updateTaxAmount = function () {
		if($scope.orderToUpdate.isTaxExempt)
		{
			$scope.orderToUpdate.taxAmount = 0;
		}
		else
		{
			$scope.orderToUpdate.taxAmount = 
				$scope.orderToUpdate.subtotal 
				* $scope.companySettings.taxRate;
		}
	};

	$scope.updateDeliveryFee = function () {
		if($scope.orderToUpdate.hasDeliveryFee)
		{
			$scope.orderToUpdate.deliveryFee = 
				$scope.companySettings.standardDeliveryFee;
		}
		else
		{
			$scope.orderToUpdate.deliveryFee = 0;
		}
	};
	
	$scope.updateCateringFee = function() {
		if($scope.orderToUpdate.isBilledOrder)
		{
			$scope.orderToUpdate.cateringFee = 
				$scope.orderToUpdate.subtotal 
				* $scope.companySettings.standardCateringRate;
		}
		else
		{
			$scope.orderToUpdate.cateringFee = 0;
		}
	};
	
	$scope.saveItem = function() {
		console.log('itemToUpdate: ' + angular.toJson($scope.itemToUpdate));
		
		$scope.itemToUpdate.activePrice = 
			$scope.itemToUpdate.menuItem.menuPrice;
		
		if($scope.itemToUpdate.menuItem.menuGroup != 'Pizza')
		{
			$scope.itemToUpdate.sauce = '';
			$scope.itemToUpdate.crust = '';
		}
		
		if($scope.itemToUpdate.orderItemId == 0)
		{
			
			$scope.orderToUpdate.items.push($scope.itemToUpdate);
			
			$scope.jsonObject = angular.toJson($scope.orderToUpdate, false);
			console.log('orderToUpdate after adding new item: ' + $scope.jsonObject);
		}
		
		$scope.showOrderItemHomeScreen = true;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.updateTotals();
	};
	
	$scope.cancelItem = function() {
		console.log('show empty item object');
		$scope.showOrderItemHomeScreen = true;
		$scope.showOrderItemUpdateScreen = false;
		$scope.showAddOrderDiscountScreen = false;
		$scope.clearItemToUpdate();
	};
	
	$scope.removeItemFromOrder = function() {
		console.log('remove item from order');
		
		
		/*
		 * if an item's orderItemId is 0 just pop it off the list because it hasn't ever made
		 * it to the backend.
		 * 
		 * if an item's orderItemId is more than 0 then it came from the backend and I need to just
		 * set the markedToRemoveFromOrder to true and let that get sent to the backend where I can
		 * look for that boolean and remove it instead of otherwise trying to figure out what is missing
		 * from an order when it gets back to the back.
		 * 
		 * I need to add an ng-show="!item.markedToRemoveFromOrder" (or ng-hide!?) so that it takes it out of the list
		 * when it's marked for deletion. I also need to set it's activePrice to 0 so that it doesn't fuck
		 * with the order anymore. updateSubtotals will need to know to look for that so that it doesn't
		 * reset the activePrice at any point.
		 */
		
		if($scope.itemToUpdate.orderItemId == 0)
		{
			$scope.orderToUpdate.items.pop($scope.itemToUpdate);
		}
		else
		{
			$scope.itemToUpdate.markedToRemoveFromOrder = true;
			$scope.itemToUpdate.activePrice = 0;
		}
		
		
		
		
		
		$scope.goToOrderItemHomeScreen();
		$scope.updateTotals();
	};
	
	$scope.showNumPeopleToFeed = function() {
		if($scope.orderToUpdate.numPeopleToFeed > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	};
	
	$scope.numPiesNeeded = function() {
		return Math.ceil($scope.orderToUpdate.numPeopleToFeed/5);

	};
	
	$scope.voidOrder = function() {
		$scope.orderToUpdate.cateringFee = 0;
		$scope.orderToUpdate.deliveryFee = 0;
		$scope.orderToUpdate.dollarDiscountAmt = 0;
		$scope.orderToUpdate.hasDeliveryFee = false;
		$scope.orderToUpdate.hasDollarDiscount = false;
		$scope.orderToUpdate.hasPercentageDiscount = false;
		$scope.orderToUpdate.isBilledOrder = false;
		$scope.orderToUpdate.isInPos = false;
		$scope.orderToUpdate.isPaid = true;
		$scope.orderToUpdate.isTaxExempt = false;
		$scope.orderToUpdate.isVoid = true;
		
		for(item of $scope.orderToUpdate.items)
		{
			item.activePrice = 0;
		}
		
		$scope.orderToUpdate.paymentType = '';
		$scope.orderToUpdate.percentageDiscountAmt = 0;
		$scope.orderToUpdate.percentageDiscountRate = 0;
		$scope.orderToUpdate.subtotal = 0;
		$scope.orderToUpdate.taxAmount = 0;
		$scope.orderToUpdate.total = 0;
		
		console.log('voided order after void: ' 
			+ angular.toJson($scope.orderToUpdate));
		
		$scope.saveOrder();
	};
	
	$scope.disableSaveOrderButton = function() {
		var result = false;
		console.log('disableSaveOrderButton?: ' +
			$scope.orderToUpdate.items.length);
		if($scope.orderToUpdate.items.length == 0)
		{
			result = true;
		}
		return result;
	};
	
	$scope.disableVoidOrderButton = function() {
		var result = false;
		console.log('disableVoidOrderButton?: ' +
			$scope.orderToUpdate.orderId);
		if($scope.orderToUpdate.orderId == 0)
		{
			result = true;
		}
		return result;
	};
	
	$scope.getSauceOptions = function () {
		console.log('getSauceOptions()');
		
		$http.get("/dgcateringapi/v1/catering/sauceoptions")
		.then(function(response) {
			$scope.sauceOptions = response.data;
			console.log('Number of Sauce Options: ' + $scope.sauceOptions.length);
		}, function(response) {
			console.log('error HTTP GET Sauce Options: ' + response.status);
		});
	};
	
	$scope.getCrustOptions = function () {
		console.log('getCrustOptions()');
		
		$http.get("/dgcateringapi/v1/catering/crustoptions")
		.then(function(response) {
			$scope.crustOptions = response.data;
			console.log('Number of Crust Options: ' + $scope.crustOptions.length);
		}, function(response) {
			console.log('error HTTP GET Crust Options: ' + response.status);
		});
	};
	
	$scope.getPaymentTypes = function () {
		console.log('getPaymentTypes()');
		
		$http.get("/dgcateringapi/v1/catering/paymenttypes")
		.then(function(response) {
			$scope.paymentTypes = response.data;
			console.log('Number of Payment Types: ' + $scope.paymentTypes.length);
		}, function(response) {
			console.log('error HTTP GET Payment Types: ' + response.status);
		});
	};
	
	$scope.getSauceOptions();
	$scope.getCrustOptions();
	$scope.getPaymentTypes();
	$scope.getAvailableMenuItems();
	$scope.getOrders();
	
//	These are setup in SQL and Jersey, need to write GET functions in
//	JavaScript file
//	$scope.sauceOptions = ['Original Red', 'Pink', 'Pesto'];
	
//	$scope.crustOptions = ['Thin', 'HandTossed'];
	
//	$scope.paymentTypes = ['Cash', 'Credit Card', 'Account'];
	
//	$scope.availableMenuItems = 
//		[
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Artie",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 1,
//		        "menuPrice": 24.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "BBQ Chicken",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 2,
//		        "menuPrice": 36.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "Buffalo Chicken",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 3,
//		        "menuPrice": 36.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Cheese",
//		        "itemName": "Cheese",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 4,
//		        "menuPrice": 18.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Chicken Supreme",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 5,
//		        "menuPrice": 26.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Classic Pepperoni",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 6,
//		        "menuPrice": 23.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "Club",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 7,
//		        "menuPrice": 34.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "DG Pepperoni",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 8,
//		        "menuPrice": 25.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Garden Supreme",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 9,
//		        "menuPrice": 26.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Greek",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 10,
//		        "menuPrice": 26.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Hawaiian",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 11,
//		        "menuPrice": 23.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "Hog",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 12,
//		        "menuPrice": 36.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Margherita",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 13,
//		        "menuPrice": 23.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "Spicy Five",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 14,
//		        "menuPrice": 26.49,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "Supreme",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 15,
//		        "menuPrice": 31.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "Underdog",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 16,
//		        "menuPrice": 31.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "1Topping",
//		        "itemName": "1-Topping",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 17,
//		        "menuPrice": 22.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier1",
//		        "itemName": "2-Topping",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 18,
//		        "menuPrice": 26.99,
//		        "size": "18"
//		    },
//		    {
//		        "dealCategory": "Tier2",
//		        "itemName": "3-Topping",
//		        "menuGroup": "Pizza",
//		        "menuItemId": 19,
//		        "menuPrice": 28.99,
//		        "size": "18"
//		    },
//		    {
//		        "itemName": "Cheese Bread",
//		        "menuGroup": "Appetizer",
//		        "menuItemId": 20,
//		        "menuPrice": 20.00,
//		        "size": "Party"
//		    },
//		    {
//		        "itemName": "Fred Bread",
//		        "menuGroup": "Appetizer",
//		        "menuItemId": 21,
//		        "menuPrice": 20.00,
//		        "size": "Party"
//		    },
//		    {
//		        "itemName": "Genies Florentinis",
//		        "menuGroup": "Appetizer",
//		        "menuItemId": 22,
//		        "menuPrice": 20.00,
//		        "size": "Party"
//		    },
//		    {
//		        "itemName": "Razorback Buns",
//		        "menuGroup": "Appetizer",
//		        "menuItemId": 23,
//		        "menuPrice": 20.00,
//		        "size": "Party"
//		    },
//		    {
//		        "itemName": "Casesar Salad",
//		        "menuGroup": "Salad",
//		        "menuItemId": 24,
//		        "menuPrice": 54.99,
//		        "size": "Catering"
//		    },
//		    {
//		        "itemName": "Mix Leaf Salad",
//		        "menuGroup": "Salad",
//		        "menuItemId": 25,
//		        "menuPrice": 54.99,
//		        "size": "Catering"
//		    }
//		];
});