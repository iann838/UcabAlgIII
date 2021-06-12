from fastapi import APIRouter
from fastapi.responses import JSONResponse

from .tasks import crypto_prices


router = APIRouter()


@router.get("/lastest/{id}")
async def read_items(id: str):
    try:
        o = crypto_prices[int(id)]
    except KeyError:
        return JSONResponse({"detail": "crypto ID not found"}, 400)
    return JSONResponse({
        "id": o["id"],
        "circulatingSupply": o["as"],
        "fullyDilutedMarketCap": o["fmc"],
        "fullyDilutedMarketCap24hpc": o["fmc24hpc"],
        "marketCap": o["mc"],
        "marketCap24hpc": o["mc24hpc"],
        "price": o["p"],
        "price1hpc": o["p1h"],
        "price24hpc": o["p24h"],
        "price7dpc": o["p7d"],
        "price30dpc": o["p30d"],
        "totalSupply": o["ts"],
        "volume": o["v"],
        "volume24hpc": o["vol24hpc"]
    })
