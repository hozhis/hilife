var memoryCache = {};
var CACHE_TTL = 86400000;// one day om seconds
var CACHE_PREFIX = "hozhis-";

function setCache(mykey, data) {
	var stamp, obj;

	stamp = Date.now();
	obj = {
		date : stamp,
		data : data
	}
	localStorage.setItem(CACHE_PREFIX + mykey, JSON.stringify(obj));
	memoryCache[mykey] = obj;
}

// fetch cached date if availabel,
// returns false if not (stale date is treated as unabailable)
function getCache(mykey) {
	var key, obj;

	// prefixed keys to prevent
	// colisions in localStorage, not likely, but
	// a good practice
	key = CACHE_PREFIX + mykey;

	if (memoryCache[key]) {

		if (memoryCache[key].date - Date.now() > CACHE_TTL) {
			return false;
		}
		return memoryCache[key].data;
	}

	obj = localStorage.getItem(key);

	if (obj) {
		obj = JSON.parse(obj);

		if (Date.now - obj.date > CACHE_TTL) {
			// cache is expired! let us purge that item
			localStorage.removeItem(key);
			delete (memoryCache[key]);
			return false;
		}
		memoryCache[key] = obj;
		return obj.data;
	}
}

function clear(mykey) {
	var key;

	// prefixed keys to prevent
	// colisions in localStorage, not likely, but
	// a good practice
	key = CACHE_PREFIX + mykey;

	localStorage.removeItem(key);
}

function clearAll() {

	localStorage.clear();
}