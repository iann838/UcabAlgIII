import asyncio

from fastapi import FastAPI

from .crypto.routes import router as crypto_router
from .crypto.tasks import start_crypto_ws, stop_crypto_ws


app = FastAPI()

app.include_router(crypto_router, prefix="/v1/crypto")

tasks = []


@app.on_event("startup")
async def startup_tasks():
    tasks.append(asyncio.create_task(start_crypto_ws()))


@app.on_event("shutdown")
async def stop_tasks():
    await stop_crypto_ws()
    for task in tasks:
        await task
