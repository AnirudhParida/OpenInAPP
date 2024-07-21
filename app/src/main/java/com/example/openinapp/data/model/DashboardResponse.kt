package com.example.openinapp.data.model

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("support_whatsapp_number") val supportWhatsappNumber: String,
    @SerializedName("extra_income") val extraIncome: Double,
    @SerializedName("total_links") val totalLinks: Int,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("today_clicks") val todayClicks: Int,
    @SerializedName("top_source") val topSource: String,
    @SerializedName("top_location") val topLocation: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("links_created_today") val linksCreatedToday: Int,
    @SerializedName("applied_campaign") val appliedCampaign: Int,
    @SerializedName("data") val data: DashboardData
)

data class DashboardData(
    @SerializedName("recent_links") val recentLinks: List<RecentLink>,
    @SerializedName("top_links") val topLinks: List<TopLink>,
    @SerializedName("favourite_links") val favouriteLinks: List<Any>,
    @SerializedName("overall_url_chart") val overallUrlChart: Any?
)

data class RecentLink(
    @SerializedName("url_id") val urlId: Int,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("smart_link") val smartLink: String,
    @SerializedName("title") val title: String,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("original_image") val originalImage: String,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("times_ago") val timesAgo: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("url_prefix") val urlPrefix: String?,
    @SerializedName("url_suffix") val urlSuffix: String,
    @SerializedName("app") val app: String,
    @SerializedName("is_favourite") val isFavourite: Boolean
)

data class TopLink(
    @SerializedName("url_id") val urlId: Int,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("smart_link") val smartLink: String,
    @SerializedName("title") val title: String,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("original_image") val originalImage: String,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("times_ago") val timesAgo: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("url_prefix") val urlPrefix: String?,
    @SerializedName("url_suffix") val urlSuffix: String,
    @SerializedName("app") val app: String,
    @SerializedName("is_favourite") val isFavourite: Boolean
)





























val defaultDashboardResponse = DashboardResponse(
    status = true,
    statusCode = 200,
    message = "success",
    supportWhatsappNumber = "6366989964",
    extraIncome = 183.49,
    totalLinks = 178,
    totalClicks = 3495,
    todayClicks = 0,
    topSource = "",
    topLocation = "",
    startTime = "",
    linksCreatedToday = 0,
    appliedCampaign = 0,
    data = DashboardData(
        recentLinks = listOf(
            RecentLink(
                urlId = 146150,
                webLink = "https://inopenapp.com/4o5qk",
                smartLink = "inopenapp.com/4o5qk",
                title = "651 Flats for Rent in Kormangla Bangalore, Bangalore Karnataka Without Brokerage - NoBroker Rental Properties in Kormangla Bangalore Karnataka Without Brokerage",
                totalClicks = 345,
                originalImage = "https://assets.nobroker.in/nb-new/public/List-Page/ogImage.png",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-03-15T07:33:50.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "4o5qk",
                app = "nobroker",
                isFavourite = false
            ),
            RecentLink(
                urlId = 146110,
                webLink = "https://inopenapp.com/estt3",
                smartLink = "inopenapp.com/estt3",
                title = "Dailyhunt",
                totalClicks = 159,
                originalImage = "https://m.dailyhunt.in/assets/img/apple-touch-icon-72x72.png?mode=pwa&ver=2.0.76",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-03-09T08:00:05.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "estt3",
                app = "dailyhunt",
                isFavourite = false
            ),
            RecentLink(
                urlId = 146061,
                webLink = "https://inopenapp.com/7113t",
                smartLink = "inopenapp.com/7113t",
                title = "MSI Katana GF66 Thin, Intel 12th Gen. i5-12450H, 40CM FHD 144Hz Gaming Laptop (16GB/512GB NVMe SSD/Windows 11 Home/Nvidia RTX3050Ti 4GB GDDR6/Black/2.25Kg), 12UD-640IN : Amazon.in: Computers & Accessories",
                totalClicks = 102,
                originalImage = "https://m.media-amazon.com/images/I/81c+XOq0b+L._SY450_.jpg",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-02-23T11:45:54.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "7113t",
                app = "amazon",
                isFavourite = false
            ),
            RecentLink(
                urlId = 145873,
                webLink = "https://inopenapp.com/juixo",
                smartLink = "inopenapp.com/juixo",
                title = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!",
                totalClicks = 81,
                originalImage = "https://www.flipkart.com/apple-touch-icon-57x57.png",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-02-20T04:59:00.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "juixo",
                app = "flipkart",
                isFavourite = false
            ),
            RecentLink(
                urlId = 144236,
                webLink = "https://inopenapp.com/h2hok",
                smartLink = "inopenapp.com/h2hok",
                title = "Programming Jokes & MeMes | The gods have spoken",
                totalClicks = 83,
                originalImage = "https://scontent-iad3-2.xx.fbcdn.net/v/t39.30808-6/325385014_1393046418172272_8557035725717444936_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=7fc0be&_nc_ohc=YYNdHpdCbiAAX9iHO5V&_nc_ht=scontent-iad3-2.xx&oh=00_AfCk2FYoD4WCCp3bqnjBxcxhQ8MEAxCw9xyInnM5sBO0VA&oe=63CD146D",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-01-18T05:40:39.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "h2hok",
                app = "facebook",
                isFavourite = false
            )
        ),
        topLinks = listOf(
            TopLink(
                urlId = 81169,
                webLink = "https://dream.inopenapp.com/vid",
                smartLink = "dream.inopenapp.com/vid",
                title = "YouTube",
                totalClicks = 1019,
                originalImage = "https://www.youtube.com/img/desktop/yt_1200.png",
                thumbnail = null,
                timesAgo = "2 yr ago",
                createdAt = "2021-12-17T10:36:05.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = "dream",
                urlSuffix = "vid",
                app = "youtube",
                isFavourite = false
            ),
            TopLink(
                urlId = 98953,
                webLink = "https://boyceavenue.inopenapp.com/boyce-avenue",
                smartLink = "boyceavenue.inopenapp.com/boyce-avenue",
                title = "Can't Help Falling In Love - Elvis Presley (Boyce Avenue acoustic cover) on Spotify & Apple",
                totalClicks = 1012,
                originalImage = "https://i.ytimg.com/vi/G0WTFfZqjz0/maxresdefault.jpg",
                thumbnail = null,
                timesAgo = "2 yr ago",
                createdAt = "2022-01-12T13:57:49.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = "boyceavenue",
                urlSuffix = "boyce-avenue",
                app = "youtube",
                isFavourite = false
            ),
            TopLink(
                urlId = 146150,
                webLink = "https://inopenapp.com/4o5qk",
                smartLink = "inopenapp.com/4o5qk",
                title = "651 Flats for Rent in Kormangla Bangalore, Bangalore Karnataka Without Brokerage - NoBroker Rental Properties in Kormangla Bangalore Karnataka Without Brokerage",
                totalClicks = 345,
                originalImage = "https://assets.nobroker.in/nb-new/public/List-Page/ogImage.png",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-03-15T07:33:50.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "4o5qk",
                app = "nobroker",
                isFavourite = false
            ),
            TopLink(
                urlId = 140627,
                webLink = "https://amazon.inopenapp.com/b01n5qh183",
                smartLink = "amazon.inopenapp.com/b01n5qh183",
                title = "Match Women's Long Sleeve Flannel Plaid Shirt at Amazon Women’s Clothing store",
                totalClicks = 197,
                originalImage = "https://m.media-amazon.com/images/I/51rE6aQt2fL._AC_.jpg",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2022-09-23T19:59:49.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = "amazon",
                urlSuffix = "b01n5qh183",
                app = "amazon",
                isFavourite = false
            ),
            TopLink(
                urlId = 146110,
                webLink = "https://inopenapp.com/estt3",
                smartLink = "inopenapp.com/estt3",
                title = "Dailyhunt",
                totalClicks = 159,
                originalImage = "https://m.dailyhunt.in/assets/img/apple-touch-icon-72x72.png?mode=pwa&ver=2.0.76",
                thumbnail = null,
                timesAgo = "1 yr ago",
                createdAt = "2023-03-09T08:00:05.000Z",
                domainId = "inopenapp.com/",
                urlPrefix = null,
                urlSuffix = "estt3",
                app = "dailyhunt",
                isFavourite = false
            )
        ),
        favouriteLinks = emptyList(),
        overallUrlChart = null
    )
)






