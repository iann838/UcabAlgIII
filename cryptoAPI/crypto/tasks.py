import asyncio
from datetime import datetime
import aiohttp

crypto_prices = {}

ws_status = {
    "keep_listening": True,
    "stopped_listening": False,
}


async def start_crypto_ws():

    headers = {
        "Host": "stream.coinmarketcap.com",
        "Connection": "Upgrade",
        "Pragma": "no-cache",
        "Cache-Control": "no-cache",
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36",
        "Upgrade": "websocket",
        "Origin": "https://coinmarketcap.com",
        "Sec-WebSocket-Version": "13",
        "Accept-Encoding": "gzip, deflate, br",
        "Accept-Language": "en,zh-CN;q=0.9,zh;q=0.8,es;q=0.7",
        "Sec-WebSocket-Key": "dYpkElc2m6SucJpg5EY//w==",
        "Sec-WebSocket-Extensions": "permessage-deflate; client_max_window_bits",
    }

    body = {
        "method": "subscribe",
        "id": "price",
        "data": {
            "cryptoIds": [
                1, 1027, 825, 1839, 2010, 74, 52, 3408, 6636, 7083, 1831, 2, 5426, 4687, 1975, 3890, 2416, 512, 8916,
                # 3717, 3077, 1321, 2280, 4943, 1958, 1765, 328, 7278, 5034, 1376, 3602, 4030, 1518, 4195, 1720, 7186, 3635,
                # 2011, 4023, 6945, 3794, 3822, 4172, 4256, 5994, 2502, 5805, 3957, 3718, 7129, 1168, 4066, 2700, 131, 4157,
                # 4642, 1274, 5692, 2394, 1437, 2563, 873, 6892, 5864, 2682, 6535, 2469, 2694, 5665, 6758, 1966, 2130, 2586,
                # 3330, 4847, 2083, 2135, 1697, 8335, 1698, 1684, 109, 1567, 6538, 3897, 1727, 3945, 2566, 7064, 6719,
                # 1896, 4779, 1042, 5617, 1808, 5567, 2499, 2087, 3513, 2577, 9023, 9022
            ],
            "index": "detail"
        }
    }

    async with aiohttp.ClientSession() as session:
        async with session.ws_connect('wss://stream.coinmarketcap.com/price/latest', headers=headers) as ws:
            await ws.send_json(body)
            try:
                while ws_status["keep_listening"]:
                    msg = await ws.receive_json()
                    crypto_prices[msg["d"]["cr"]["id"]] = msg["d"]["cr"]
            except KeyboardInterrupt:
                pass
    ws_status["stopped_listening"] = True


async def stop_crypto_ws():
    ws_status["keep_listening"] = False
    while not ws_status["stopped_listening"]:
        await asyncio.sleep(0.1)
