using System;
using System.Collections.Concurrent;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using NUnit.Framework;

namespace S3.Threading.UnitTests
{
	[TestFixture]
	class ManualResetEventAsyncTests
	{
		[Test]
		public async Task CanExecute()
		{
			var target = new ManualResetEventAsync(false);
			var actual = new ConcurrentBag<int>();
			var task1 = Task.Factory.StartNew(async () => actual.Add(await _Execute(target, 1)));
			var task2 = Task.Factory.StartNew(async () => actual.Add(await _Execute(target, 2)));
			Thread.Sleep(TimeSpan.FromMilliseconds(500));
			CollectionAssert.IsEmpty(actual);

			target.Set();
			Thread.Sleep(TimeSpan.FromMilliseconds(500));

			CollectionAssert.AreEquivalent(Enumerable.Range(1, 2), actual);
			Assert.IsTrue(task1.IsCompletedSuccessfully);
			Assert.IsTrue(task2.IsCompletedSuccessfully);
		}

		private async Task<int> _Execute(ManualResetEventAsync target, int id)
		{

			await target.WaitAsync();
			return id;
		}
	}
}
